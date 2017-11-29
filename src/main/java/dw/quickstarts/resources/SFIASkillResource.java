package dw.quickstarts.resources;

import com.codahale.metrics.annotation.Timed;
import dw.quickstarts.SFIASkill;
import dw.quickstarts.SFIASkillList;
import dw.quickstarts.annotations.LoginRequired;
import dw.quickstarts.dao.SFIASkillDAO;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by callumbarnes on 31/10/2017.
 */
@Path("/sfiaskill")
public class SFIASkillResource {


    private final SFIASkillDAO sfiaSkillDAO;

    public SFIASkillResource(SFIASkillDAO sfiaSkillDAO)
    {
        this.sfiaSkillDAO = sfiaSkillDAO;
    }


    public void testing()
    {

        List<SFIASkill> listOfSFIASkills = sfiaSkillDAO.findAllSkills();
        Map<String, SFIALevelID> skillWithLevels = new HashMap<>();

        for(SFIASkill sfiaSkill : listOfSFIASkills)
        {
            SFIALevelID currentSFIASkill = skillWithLevels.get(sfiaSkill.getSkill());

            if(currentSFIASkill == null)
            {
                skillWithLevels.put(sfiaSkill.getSkill(), new SFIALevelID(sfiaSkill.getId(), sfiaSkill.getLevel()) );
            }
            else{
                currentSFIASkill.addLevelID(sfiaSkill.getId(), sfiaSkill.getLevel());
            }

        }

        System.out.println();

    }


    @GET
    @Path("/list")
    @Timed
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, SFIALevelID> getSFIASkillsList(@Session HttpSession session, @Context UriInfo uriInfo) throws Exception
    {

        List<SFIASkill> listOfSFIASkills = sfiaSkillDAO.findAllSkills();
        Map<String, SFIALevelID> skillWithLevels = new HashMap<>();

        for(SFIASkill sfiaSkill : listOfSFIASkills)
        {
            SFIALevelID currentSFIASkill = skillWithLevels.get(sfiaSkill.getSkill());

            if(currentSFIASkill == null)
            {
                skillWithLevels.put(sfiaSkill.getSkill(), new SFIALevelID(sfiaSkill.getId(), sfiaSkill.getLevel()) );
            }
            else{
                currentSFIASkill.addLevelID(sfiaSkill.getId(), sfiaSkill.getLevel());
            }

        }


        return skillWithLevels;

    }

    private class SFIALevelID {

        HashMap<Long, Integer> levelIDMap = new HashMap<>();

        public SFIALevelID(long id, int level)
        {
            levelIDMap.put(id, level);
        }

        public void addLevelID(long id, int level)
        {
            levelIDMap.put(id, level);
        }

    }

}
