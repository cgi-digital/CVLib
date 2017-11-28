package dw.quickstarts.dao.mappers;

import dw.quickstarts.SFIASkillList;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by callumbarnes on 01/11/2017.
 */
public class SFIASkillListMapper implements ResultSetMapper<SFIASkillList> {

    @Override
    public SFIASkillList map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new SFIASkillList(resultSet.getLong("id"),
                resultSet.getString("skill"),
                resultSet.getInt("level")
        );
    }

}
