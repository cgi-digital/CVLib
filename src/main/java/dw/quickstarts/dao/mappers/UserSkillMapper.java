package dw.quickstarts.dao.mappers;

import dw.quickstarts.UserSkill;
import dw.quickstarts.UserSkillView;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class UserSkillMapper implements ResultSetMapper<UserSkill> {

    @Override
    public UserSkill map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new UserSkill(resultSet.getLong("userid"),
                resultSet.getLong("skillid"),
                resultSet.getInt("level")
        );
    }

}
