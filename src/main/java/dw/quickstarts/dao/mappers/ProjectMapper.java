package dw.quickstarts.dao.mappers;

import dw.quickstarts.Project;
import dw.quickstarts.Qualification;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class ProjectMapper implements ResultSetMapper<Project> {
    @Override
    public Project map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Project(resultSet.getLong("id"),
                resultSet.getLong("userid"),
                resultSet.getString("employer"),
                resultSet.getString("project"),
                resultSet.getString("role"),
                resultSet.getString("summary")
        );
    }
}
