package dw.quickstarts.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import dw.quickstarts.User;
import dw.quickstarts.dao.mappers.UserMapper;

import java.util.List;
import java.util.Set;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select * from users where username = :username")
    User findByUsername(@Bind("username") String username);

    @SqlQuery("select * from users where id = :id")
    User findById(@Bind("id") Long id);

    @SqlQuery("select * from users")
    List<User> findAll();

    @SqlUpdate("insert into users (username,firstname,lastname,salt,password,admin,disabled) values (:username, :firstname, :lastname, :salt,:password,:admin, false)")
    void registerUser(@Bind("username") String username,
                      @Bind("firstname") String firstname,
                      @Bind("lastname") String lastname,
                      @Bind("salt") String salt,
                      @Bind("password") String password,
                      @Bind("admin") boolean admin);

    @SqlUpdate("update users set firstname = :firstname, lastname = :lastname, address = :address, title = :title, summary = :summary where username = :username")
    void updateUserDetails(@Bind("username") String username,
                      @Bind("firstname") String firstname,
                      @Bind("lastname") String lastname,
                      @Bind("address") String address,
                      @Bind("title") String title,
                      @Bind("summary") String summary);

    @SqlUpdate("update users set salt = :salt, password = :password where username = :username")
    void updateUserCredentials(@Bind("username") String username,
                               @Bind("salt") String salt,
                               @Bind("password") String password);


    @SqlUpdate("update users set users.admin = true where users.id = :id")
    void promoteUserToAdministrator(@Bind("id") Long id);

    @SqlUpdate("update users set users.disabled = :disabled where users.id = :id")
    void updateUserEnablement(@Bind("id") Long id, @Bind("disabled") boolean disabled);

    void close();
}
