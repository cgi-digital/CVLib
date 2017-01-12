package dw.quickstarts.dao;

import dw.quickstarts.Project;
import dw.quickstarts.dao.mappers.ProjectMapper;
import dw.quickstarts.dao.mappers.QualificationMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by koskinasm on 11/01/2017.
 */
@RegisterMapper(ProjectMapper.class)
public interface ProjectDAO {

    @SqlQuery("select * from projects where userid = :userid")
    List<Project> findUserProjects(@Bind("userid") long userid);

    @SqlQuery("select * from projects where id = :id")
    List<Project> findUserProjectById(@Bind("id") long id);

    @SqlUpdate("insert into projects (userid, employer, project, role, summary) values (:userid , :employer, :project, :role, :summary)")
    void addProject(@Bind("userid") Long userid,
                    @Bind("employer") String employer,
                    @Bind("project") String project,
                    @Bind("role") String role,
                    @Bind("summary") String summary);

    @SqlUpdate("update projects set employer = :employer, project = :project, role = :role, summary = :summary where id = :id AND userid = :userid")
    void updateProject(@Bind("id") Long id,
                       @Bind("userid") Long userid,
                       @Bind("employer") String employer,
                       @Bind("project") String project,
                       @Bind("role") String role,
                       @Bind("summary") String summary);

    @SqlUpdate("delete from projects where id = :id AND userid = :userid")
    void deleteProject(@Bind("id") Long id,
                       @Bind("userid") Long userid);

}
