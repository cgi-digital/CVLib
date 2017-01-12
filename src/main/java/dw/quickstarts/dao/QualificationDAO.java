package dw.quickstarts.dao;

import dw.quickstarts.Qualification;
import dw.quickstarts.dao.mappers.QualificationMapper;
import dw.quickstarts.dao.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by koskinasm on 11/01/2017.
 */
@RegisterMapper(QualificationMapper.class)
public interface QualificationDAO {

    @SqlQuery("select * from qualifications where userid = :userid")
    List<Qualification> findUserQualifications(@Bind("userid") long userid);

    @SqlQuery("select * from qualifications where id = :id")
    Qualification findUserQualificationById(@Bind("id") long id);

    @SqlUpdate("insert into qualifications (userid, qualification) values (:userid , :qualification)")
    void addQualification(@Bind("userid") Long userid, @Bind("qualification") String qualification);

    @SqlUpdate("update qualifications set qualification = :qualification where id = :id AND userid = :userid")
    void updateQualification(@Bind("id") Long id, @Bind("userid") Long userid, @Bind("qualification") String qualification);

    @SqlUpdate("delete from qualifications where id = :id AND userid = :userid")
    void deleteQualification(@Bind("id") Long id, @Bind("userid") Long userid);
}
