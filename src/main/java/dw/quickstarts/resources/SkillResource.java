package dw.quickstarts.resources;

import com.codahale.metrics.annotation.Timed;
import dw.quickstarts.Skill;
import dw.quickstarts.annotations.LoginRequired;
import dw.quickstarts.dao.SkillDAO;
import io.dropwizard.jersey.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by callumbarnes on 13/12/2017.
 */
@Path("/skill")
public class SkillResource {

    private final Logger LOGGER = LoggerFactory.getLogger(SkillResource.class);

    private final SkillDAO skillDAO;

    public SkillResource(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @GET
    @Path("/all")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSkills(@Context HttpServletRequest request, @Session HttpSession session, @Context UriInfo uriInfo)
    {
        List<Skill> skillList = skillDAO.findAllSkills();

        return Response.status(Response.Status.OK).entity(skillList).build();
    }

}
