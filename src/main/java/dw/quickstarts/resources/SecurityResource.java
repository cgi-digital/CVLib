package dw.quickstarts.resources;

import com.codahale.metrics.annotation.Timed;
import dw.quickstarts.ExceptionFormatter;
import dw.quickstarts.User;
import dw.quickstarts.dao.UserDAO;
import dw.quickstarts.utilities.URITools;
import dw.quickstarts.views.security.LoginView;
import dw.quickstarts.views.security.RegisterView;
import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Path("/security")
public class SecurityResource {
    private final Logger LOGGER = LoggerFactory.getLogger(SecurityResource.class);
    private final UserDAO userDAO;

    private static HashMap<String,String> messages = new HashMap<>();
    static
    {
        messages.put("REGISTRATION","Successfully registered. Please login to your account");
        messages.put("REGISTRATION_ERR_1","The passwords you provided don't match.");
        messages.put("REGISTRATION_ERR_2","The provided username is already in use");
        messages.put("LOGIN_ERR","Invalid credentials.");
    }

    private String loginLocation = "login";
    private String registerLocation = "register";

    public SecurityResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @Path("/login")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleLogin(@Session HttpSession session,
                                @Context HttpServletRequest req,
                                @Context UriInfo uriInfo,
                                @FormParam("Username") String username,
                                @FormParam("Password") String password) throws Exception {
        //TODO escape user input
        User u = null;
        if (StringUtils.isNotBlank(username)) {
            u = userDAO.findByUsername(username);
        }
        if (u != null) {
            String passwordGuess = String.format("%s%s", u.getSalt(), password);
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(passwordGuess.getBytes(StandardCharsets.UTF_8));
                String hexHash = Hex.encodeHexString(hash);
                if (hexHash.compareToIgnoreCase(u.getPassword()) == 0) {
                    session.setAttribute("username", u.getUsername());
                    session.setAttribute("userid", u.getId());
                    session.setAttribute("authenticated", true);
                    if(u.isAdmin())
                    {
                        session.setAttribute("admin", true);
                    }

                    if (req.getHeader("X-Forwarded-For") != null) {
                        LOGGER.info(String.format("Successful login for %s from ip %s (%s)", u.getUsername(),
                                req.getHeader("X-Forwarded-For"),
                                req.getRemoteHost()));
                    } else {
                        LOGGER.info(String.format("Successful login for %s from ip %s",
                                u.getUsername(), req.getRemoteHost()));
                    }
                    return Response.seeOther(uriInfo.getBaseUriBuilder().path(UserResource.class).build()).build();
                }
            } catch (Exception e) {
                LOGGER.error(ExceptionFormatter.formatAsString(e));
            }
        }

        // Log an error message
        StringBuilder sb = new StringBuilder();
        sb.append("Failed login");
        if (StringUtils.isNotBlank(username)) {
            sb.append(String.format(" for user '%s'", username));
        }
        if (req.getHeader("X-Forwarded-For") != null) {
            sb.append(String.format(" from ip %s (%s)", req.getHeader("X-Forwarded-For"), req.getRemoteHost()));
        } else {
            sb.append(String.format(" from ip %s", req.getRemoteHost()));
        }
        LOGGER.info(sb.toString());

        // Sleep to avoid hack attack
        Thread.sleep(1000);
        session.setAttribute("username", username);
        session.setAttribute("authenticated", false);

        return Response.status(Response.Status.BAD_REQUEST).entity(messages.get("LOGIN_ERR")).build();
    }

    @POST
    @Path("/logout")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleLogout(@Session HttpSession session,
                                 @Context UriInfo uriInfo) {
        session.setAttribute("authenticated", false);

        return Response.status(Response.Status.OK).entity(uriInfo.getBaseUriBuilder()
                .path(SecurityResource.class)
                .path("/login").build())
                .build();
    }

    @POST
    @Path("/register")
    @Timed
    public Response register(@Session HttpSession session,
                             @Context HttpServletRequest req,
                             @Context UriInfo uriInfo,
                             @FormParam("Username") String username,
                             @FormParam("FirstName") String firstname,
                             @FormParam("LastName") String lastname,
                             @FormParam("EmailAddress") String emailAddress,
                             @FormParam("Password") String password,
                             @FormParam("ReTypePassword") String reTypePassword) throws Exception {

        //TODO escape user input

        if (password.equals(reTypePassword)) {
            if(userDAO.findByUsername(username) == null){

                String salt = RandomStringUtils.randomAlphanumeric(12);
                String saltedPassword = String.format("%s%s", salt, password);

                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                }
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
                String hexHash = Hex.encodeHexString(hash);

                userDAO.registerUser(username, firstname, lastname, emailAddress, salt, hexHash,false);

                LOGGER.info(messages.get("REGISTRATION"));

                return handleLogin(session,req,uriInfo,username,password);
            }
            else{
                //username exists
                LOGGER.info("REGISTRATION_ERR_2");
                return Response.status(Response.Status.BAD_REQUEST).entity(messages.get("REGISTRATION_ERR_2")).build();
            }
        }
        else {
            //password mismatch
            LOGGER.info("REGISTRATION_ERR_1");
            return Response.status(Response.Status.BAD_REQUEST).entity(messages.get("REGISTRATION_ERR_1")).build();
        }
    }
}
