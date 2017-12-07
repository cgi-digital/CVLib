package dw.quickstarts.resources;

import com.codahale.metrics.annotation.Timed;
import dw.quickstarts.*;
import dw.quickstarts.annotations.LoginRequired;
import dw.quickstarts.dao.ProjectDAO;
import dw.quickstarts.dao.QualificationDAO;
import dw.quickstarts.dao.SkillDAO;
import dw.quickstarts.dao.UserDAO;
import dw.quickstarts.utilities.URITools;
import io.dropwizard.jersey.sessions.Session;
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

@Path("/user")
public class UserResource {
    private final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private final UserDAO userDAO;
    private final SkillDAO skillDAO;
    private final QualificationDAO qualificationDAO;
    private final ProjectDAO projectDAO;

    public UserResource(UserDAO userDAO, SkillDAO skillDAO, QualificationDAO qualificationDAO, ProjectDAO projectDAO) {
        this.userDAO = userDAO;
        this.skillDAO = skillDAO;
        this.qualificationDAO = qualificationDAO;
        this.projectDAO = projectDAO;
    }

    @GET
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getContent(@Session HttpSession session, @Context UriInfo uriInfo) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        List<UserSkillView> skills = skillDAO.findUserSkills(id);
        List<Qualification> qualifications = qualificationDAO.findUserQualifications(id);
        List<Project> projects = projectDAO.findUserProjects(id);

        return Profile.getFullProfile(user,skills,qualifications,projects);
    }

    @POST
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserDetails(@Session HttpSession session, @Context UriInfo uriInfo,
                                           @FormParam("FirstName") String firstName,
                                           @FormParam("LastName") String lastName,
                                           @FormParam("Address") String address,
                                           @FormParam("Title") String title,
                                           @FormParam("Summary") String summary) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        userDAO.updateUserDetails(user.getEmail(),firstName,lastName,address,title,summary);

        return Response.status(Response.Status.OK).build();
    }




//    @GET
//    @Path("/skills")
//    @Timed
//    @LoginRequired
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Skill> getSkills(@Session HttpSession session, @Context UriInfo uriInfo) throws Exception {
//
//        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
//        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");
//
//        Long id = (Long) session.getAttribute("userid");
//        User user = userDAO.findById(id);
//
//        List<Skill> skills = skillDAO.findUserSkills(user.getId());
//
//        return skills;
//    }

    @POST
    @Path("/skills")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSkill(@Session HttpSession session, @Context UriInfo uriInfo,
                                     @FormParam("skillName") String skill, @FormParam("level") int level) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long userid = (Long) session.getAttribute("userid");
        User user = userDAO.findById(userid);


        //skillDAO.addSkill(user.getId(),skill,level);
        Skill newUserSkill = skillDAO.findSkillByName(skill);

        if(newUserSkill == null)
        {
            // add skill to db
            skillDAO.addSkill(skill, "Other");
            newUserSkill = skillDAO.findSkillByName(skill);
            skillDAO.addUserSkill(userid, newUserSkill.getId(), level);

        }
        else {

            if(skillDAO.findUserSkillById(userid, newUserSkill.getId()) == null)
                skillDAO.addUserSkill(userid, newUserSkill.getId(), level);

        }


        return Response.status(Response.Status.OK).entity(skillDAO.findUserSkills(userid)).build();
    }

    @PUT
    @Path("/skills/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSkill(@Session HttpSession session, @Context UriInfo uriInfo,
                                        @FormParam("skill") String skill, @FormParam("level") int level,
                                        @PathParam("id") long skillId) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);


        skillDAO.updateSkill(skillId,user.getId(),skill,level);

        return Response.status(Response.Status.OK).entity(skillDAO.findUserSkills(id)).build();

    }

    @DELETE
    @Path("/skills/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSkill(@Session HttpSession session, @Context UriInfo uriInfo,@PathParam("id") long skillId) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        //TODO DAO
        skillDAO.deleteSkill(skillId,user.getId());

        return Response.status(Response.Status.OK).entity(skillDAO.findUserSkills(id)).build();
    }


    @GET
    @Path("/qualifications")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public List<Qualification> getQualifications(@Session HttpSession session, @Context UriInfo uriInfo) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        List<Qualification> qualifications = qualificationDAO.findUserQualifications(id);

        return qualifications;
    }

    @POST
    @Path("/qualifications")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQualification(@Session HttpSession session, @Context UriInfo uriInfo,
                                                @FormParam("qualification") String qualification) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        qualificationDAO.addQualification(user.getId(),qualification);

        return Response.status(Response.Status.OK).entity(qualificationDAO.findUserQualifications(id)).build();
    }

    @PUT
    @Path("/qualifications/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQualification(@Session HttpSession session, @Context UriInfo uriInfo,
                                                @FormParam("qualification") String qualification,
                                                @PathParam("id") long qualificationId) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        qualificationDAO.updateQualification(qualificationId,user.getId(),qualification);

        return Response.status(Response.Status.OK).entity(qualificationDAO.findUserQualifications(id)).build();

    }

    @DELETE
    @Path("/qualifications/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteQualification(@Session HttpSession session, @Context UriInfo uriInfo,@PathParam("id") long qualificationId) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        //TODO DAO
        qualificationDAO.deleteQualification(qualificationId,user.getId());

        return Response.status(Response.Status.OK).entity(qualificationDAO.findUserQualifications(id)).build();
    }

    @GET
    @Path("/projects")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjects(@Session HttpSession session, @Context UriInfo uriInfo) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        List<Project> projects = projectDAO.findUserProjects(id);

        return projects;
    }

    @POST
    @Path("/projects")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProject(@Session HttpSession session, @Context UriInfo uriInfo,
                                                @FormParam("employer") String employer,
                                                @FormParam("project") String project,
                                                @FormParam("role") String role,
                                                @FormParam("summary") String summary) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        //TODO DAO
        projectDAO.addProject(user.getId(),employer,project,role,summary);
        return Response.status(Response.Status.OK).entity(projectDAO.findUserProjects(id)).build();
    }

    @PUT
    @Path("/projects/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(@Session HttpSession session, @Context UriInfo uriInfo,
                                             @FormParam("employer") String employer,
                                             @FormParam("project") String project,
                                             @FormParam("role") String role,
                                             @FormParam("summary") String summary,
                                             @PathParam("id") long projectId
                                             ) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        projectDAO.updateProject(projectId, user.getId(),employer,project,role,summary);
        return Response.status(Response.Status.OK).entity(projectDAO.findUserProjects(id)).build();
    }

    @DELETE
    @Path("/projects/{id}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProject(@Session HttpSession session, @Context UriInfo uriInfo, @PathParam("id") long projectId) throws Exception {

        URI logoutLocation = URITools.buildURI(uriInfo, SecurityResource.class, "/logout");
        URI adminConsoleLocation = URITools.buildURI(uriInfo, AdminConsoleResource.class, "");

        Long id = (Long) session.getAttribute("userid");
        User user = userDAO.findById(id);

        projectDAO.deleteProject(projectId, user.getId());
        return Response.status(Response.Status.OK).entity(projectDAO.findUserProjects(id)).build();
    }

    @GET
    @Path("/all")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@Context HttpServletRequest request, @Session HttpSession session, @Context UriInfo uriInfo) {

        URI logoutLocation = uriInfo
                .getBaseUriBuilder()
                .path(SecurityResource.class)
                .path("/logout")
                .scheme(null)
                .build();

         List<Profile> profiles = new ArrayList<>();
         for(User other : userDAO.findAll())
         {
             profiles.add(Profile.getListProfile(other));
         }

         return Response.status(Response.Status.OK).entity(profiles).build();
    }

    @GET
    @Path("/id/{userid}")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context HttpServletRequest request, @Session HttpSession session, @Context UriInfo uriInfo, @PathParam("userid") long userid) {

        URI logoutLocation = uriInfo
                .getBaseUriBuilder()
                .path(SecurityResource.class)
                .path("/logout")
                .scheme(null)
                .build();

            User requestedUser = userDAO.findById(userid);
            if(requestedUser != null)
            {
                List<UserSkillView> skills = skillDAO.findUserSkills(userid);
                List<Qualification> qualifications = qualificationDAO.findUserQualifications(userid);
                List<Project> projects = projectDAO.findUserProjects(userid);

                return Response.status(Response.Status.OK).entity(Profile.getFullProfile(requestedUser,skills,qualifications,projects)).build();
            }
            else
            {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

    }
}
