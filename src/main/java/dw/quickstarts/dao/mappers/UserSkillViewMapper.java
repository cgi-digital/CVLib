package dw.quickstarts.dao.mappers;

import dw.quickstarts.UserSkillView;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class UserSkillViewMapper implements ResultSetMapper<UserSkillView> {

    @Override
    public UserSkillView map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new UserSkillView(resultSet.getLong("userid"),
                resultSet.getLong("skillid"),
                resultSet.getInt("level"),
                resultSet.getString("skill"),
                resultSet.getString("type")
        );
    }

}
