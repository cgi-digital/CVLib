package dw.quickstarts.resources;

import com.codahale.metrics.annotation.Timed;
import dw.quickstarts.Profile;
import dw.quickstarts.User;
import dw.quickstarts.annotations.LoginRequired;
import dw.quickstarts.dao.ProjectDAO;
import dw.quickstarts.dao.QualificationDAO;
import dw.quickstarts.dao.UserDAO;
import dw.quickstarts.utilities.URITools;
import dw.quickstarts.views.AdminConsoleView;
import dw.quickstarts.views.UserDetailsView;
import dw.quickstarts.views.UsersListView;
import dw.quickstarts.views.exceptions.UnauthorizedView;
import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koskinasm on 06/10/2016.
 */
@Path("/console")
public class AdminConsoleResource {
    private final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private final UserDAO userDAO;
    private final QualificationDAO qualificationDAO;
    private final ProjectDAO projectDAO;

    public static final String adminConsoleLocationPath = "/console";

    public AdminConsoleResource(UserDAO userDAO, QualificationDAO qualificationDAO, ProjectDAO projectDAO) {
        this.userDAO = userDAO;
        this.qualificationDAO = qualificationDAO;
        this.projectDAO = projectDAO;
    }

    @PUT
    @Path("/{userid}/admin")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response promoteUserToAdmin(@Context HttpServletRequest request, @Session HttpSession session, @Context UriInfo uriInfo, @PathParam("userid") long userid) {

        URI logoutLocation = uriInfo
                .getBaseUriBuilder()
                .path(SecurityResource.class)
                .path("/logout")
                .scheme(null)
                .build();

        Long adminID = (Long) session.getAttribute("userid");
        User currentUser = userDAO.findById(adminID);

        if (currentUser.isAdmin()) {
            User requestedUser = userDAO.findById(userid);
            if(requestedUser != null)
            {
                if(!requestedUser.isDisabled())
                {
                    userDAO.promoteUserToAdministrator(userid);
                    return Response.status(Response.Status.OK).entity(userDAO.findAll()).build();
                }
                else
                {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            }
            else
            {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @PUT //should be DELETE
    @Path("/{userid}/disable/{enablement}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserEnablement(@Context HttpServletRequest request, @Session HttpSession session, @Context UriInfo uriInfo, @PathParam("userid") long userid, @PathParam("enablement") boolean enablement) {

        URI logoutLocation = uriInfo
                .getBaseUriBuilder()
                .path(SecurityResource.class)
                .path("/logout")
                .scheme(null)
                .build();

        Long adminID = (Long) session.getAttribute("userid");
        User currentUser = userDAO.findById(adminID);

        if (currentUser.isAdmin()) {
            User requestedUser = userDAO.findById(userid);
            if(requestedUser != null)
            {
                userDAO.updateUserEnablement(userid,enablement);
                return Response.status(Response.Status.OK).entity(userDAO.findAll()).build();
            }
            else
            {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }


        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
