package dw.quickstarts.dao.mappers;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import dw.quickstarts.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("employeeid"),
                resultSet.getString("baselocation"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("title"),
                resultSet.getString("summary"),
                resultSet.getString("salt"),
                resultSet.getString("password"),
                resultSet.getBoolean("admin"),
                resultSet.getBoolean("disabled"));

    }
}
