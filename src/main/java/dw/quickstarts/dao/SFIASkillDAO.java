package dw.quickstarts.dao;

import dw.quickstarts.SFIASkill;
import dw.quickstarts.SFIASkillList;
import dw.quickstarts.dao.mappers.SFIASkillListMapper;
import dw.quickstarts.dao.mappers.SFIASkillMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by callumbarnes on 31/10/2017.
 */
public interface SFIASkillDAO {

    @SqlQuery("select id, skill, level from skillstest")
    @Mapper(SFIASkillMapper.class)
    List<SFIASkill> findAllSkills();

    @SqlQuery("select id, skill, level from skillstest")
    @Mapper(SFIASkillListMapper.class)
    List<SFIASkillList> findAllSkillsList();

}
