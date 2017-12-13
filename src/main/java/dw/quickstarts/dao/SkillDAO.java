package dw.quickstarts.dao;

import dw.quickstarts.Skill;
import dw.quickstarts.UserSkill;
import dw.quickstarts.UserSkillView;
import dw.quickstarts.dao.mappers.SkillMapper;
import dw.quickstarts.dao.mappers.UserSkillMapper;
import dw.quickstarts.dao.mappers.UserSkillViewMapper;
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

    @RegisterMapper(UserSkillViewMapper.class)
    @SqlQuery("select userskill.*, skills.skill, skills.type from skills, userskill where userskill.userid = :userid and skills.id = userskill.skillid")
    List<UserSkillView> findUserSkills(@Bind("userid") long userid);


    @RegisterMapper(UserSkillMapper.class)
    @SqlQuery("select * from userskill where userid = :userid and skillid = :skillid")
    UserSkill findUserSkillById(@Bind("userid") long userid, @Bind("skillid") long skillid);

    @SqlQuery("select * from skills where UPPER(skill) = UPPER(:name)")
    Skill findSkillByName(@Bind("name") String name);

    @SqlUpdate("insert into userskill (userid, skillid, level) values (:userid, :skillid, :level)")
    void addUserSkill(@Bind("userid") Long userid, @Bind("skillid") Long skillid, @Bind("level") int level);

    @SqlUpdate("insert into skills (skill, type) values (:skill, :type)")
    void addSkill(@Bind("skill") String skill, @Bind("type") String type);

    @SqlUpdate("update skills set skill = :skill, level = :level where id = :id AND userid = :userid")
    void updateSkill(@Bind("id") Long id, @Bind("userid") Long userid, @Bind("skill") String skill, @Bind("level") int level);

    @SqlUpdate("update userskill set level = :level WHERE userid = :userid AND skillid = :skillid")
    void updateUserSkill(@Bind("userid") Long userid, @Bind("skillid") Long skillid, @Bind("level") int level);

    @SqlUpdate("delete from skills where id = :id AND userid = :userid")
    void deleteSkill(@Bind("id") Long id, @Bind("userid") Long userid);

    @SqlUpdate("delete from skills where userid = :id")
    void deleteAllUserSkills(@Bind("id") Long id);
}
