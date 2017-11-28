package dw.quickstarts.resources;

import dw.quickstarts.SFIASkill;
import dw.quickstarts.SFIASkillList;
import dw.quickstarts.dao.SFIASkillDAO;

import java.util.List;

/**
 * Created by callumbarnes on 31/10/2017.
 */
public class SkillTestResource {


    //Maybe have server-side transform into a SkillList object?

    private final SFIASkillDAO sfiaSkillDAO;

    public SkillTestResource(SFIASkillDAO sfiaSkillDAO)
    {
        this.sfiaSkillDAO = sfiaSkillDAO;
    }


    public void testing()
    {


        List<SFIASkill> skillTests = sfiaSkillDAO.findAllSkills();

        List<SFIASkillList> skillTestLists = sfiaSkillDAO.findAllSkillsList();

        System.out.println();

    }

}
