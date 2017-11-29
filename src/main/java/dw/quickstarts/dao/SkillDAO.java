package dw.quickstarts.dao;

import dw.quickstarts.Qualification;
import dw.quickstarts.Skill;
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

    @SqlQuery("select * from skills where userid = :userid")
    List<Skill> findUserSkills(@Bind("userid") long userid);

    @SqlQuery("select * from skills where id = :id")
    Skill findUserSkillById(@Bind("id") long id);

    @SqlUpdate("insert into skills (userid, skill, level) values (:userid , :skill, :level)")
    void addSkill(@Bind("userid") Long userid, @Bind("skill") String skill, @Bind("level") int level);

    @SqlUpdate("update skills set skill = :skill, level = :level where id = :id AND userid = :userid")
    void updateSkill(@Bind("id") Long id, @Bind("userid") Long userid, @Bind("skill") String skill, @Bind("level") int level);

    @SqlUpdate("delete from skills where id = :id AND userid = :userid")
    void deleteSkill(@Bind("id") Long id, @Bind("userid") Long userid);

    @SqlUpdate("delete from skills where userid = :id")
    void deleteAllUserSkills(@Bind("id") Long id);
}
