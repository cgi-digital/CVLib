package dw.quickstarts.dao;

import dw.quickstarts.Qualification;
import dw.quickstarts.Skill;
import dw.quickstarts.UserSkill;
import dw.quickstarts.dao.mappers.QualificationMapper;
import dw.quickstarts.dao.mappers.SkillMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by koskinasm on 11/01/2017.
 */
@RegisterMapper(SkillMapper.class)
public interface SkillDAO {

    @SqlQuery("select userskill.*, skills.skill, skills.type from skills, userskill where userskill.userid = :userid")
    List<UserSkill> findUserSkills(@Bind("userid") long userid);

    @SqlQuery("select * from skills where id = :id")
    Skill findUserSkillById(@Bind("id") long id);

    @SqlQuery("select * from skills where UPPER(skill) = UPPER(:name)")
    Skill findSkillByName(@Bind("name") String name);

    @SqlUpdate("insert into userskill (userid, skillid, level) values (:userid, :skillid, :level)")
    void addUserSkill(@Bind("userid") Long userid, @Bind("skillid") Long skillid, @Bind("level") int level);

    @SqlUpdate("insert into skills (skill, type) values (:skill, :type)")
    Skill addSkill(@Bind("skill") String skill, @Bind("type") String type);

    @SqlUpdate("update skills set skill = :skill, level = :level where id = :id AND userid = :userid")
    void updateSkill(@Bind("id") Long id, @Bind("userid") Long userid, @Bind("skill") String skill, @Bind("level") int level);

    @SqlUpdate("delete from skills where id = :id AND userid = :userid")
    void deleteSkill(@Bind("id") Long id, @Bind("userid") Long userid);
}
