package dw.quickstarts.dao.mappers;

import dw.quickstarts.Qualification;
import dw.quickstarts.Skill;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class SkillMapper implements ResultSetMapper<Skill> {
    @Override
    public Skill map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Skill(resultSet.getLong("id"),
                resultSet.getLong("userid"),
                resultSet.getString("skill"),
                resultSet.getInt("level")
        );
    }
}
