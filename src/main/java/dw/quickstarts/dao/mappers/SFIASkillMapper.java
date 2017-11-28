package dw.quickstarts.dao.mappers;

import dw.quickstarts.SFIASkill;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by callumbarnes on 31/10/2017.
 */
public class SFIASkillMapper implements ResultSetMapper<SFIASkill> {

    @Override
    public SFIASkill map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new SFIASkill(resultSet.getLong("id"),
                resultSet.getString("skill"),
                resultSet.getInt("level")
        );
    }

}
