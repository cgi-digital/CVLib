package dw.quickstarts.dao.mappers;

import dw.quickstarts.PhoneNumber;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by callumbarnes on 14/12/2017.
 */
public class PhoneNumMapper implements ResultSetMapper<PhoneNumber> {

    @Override
    public PhoneNumber map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new PhoneNumber(resultSet.getString("phonenumber"),
                resultSet.getString("type"),
                resultSet.getLong("userid")
        );
    }

}
