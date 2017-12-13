package dw.quickstarts.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import dw.quickstarts.User;
import dw.quickstarts.dao.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.List;
import java.util.Set;

@UseStringTemplate3StatementLocator
@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select * from users where username = :username")
    User findByUsername(@Bind("username") String username);

    @SqlQuery("select * from users where email = :email")
    User findByEmail(@Bind("email") String email);

    @SqlQuery("select * from users where id = :id")
    User findById(@Bind("id") Long id);

    @SqlQuery("select * from users where admin = true")
    List<User> findAllAdmins();

    @SqlQuery("select * from users where UPPER(firstname) like UPPER(:firstname) and UPPER(lastname) like UPPER(:lastname)")
    List<User> findByFullName(@Bind("firstname") String firstname,
                              @Bind("lastname") String lastname);

    @SqlQuery("select * from users where baselocation = :baselocation")
    List<User> findByBaseLocation(@Bind("baselocation") String baselocation);

    
    @SqlQuery("SELECT distinct users.* FROM USERS, SKILLS, USERSKILL WHERE USERSKILL.USERID = USERS.ID AND USERSKILL.SKILLID = SKILLS.ID AND UPPER(SKILLS.SKILL) IN (<skills>) group by USERSKILL.USERID having count(*) = :skillCount")
    List<User> findBySkill(@BindIn("skills") List<String> skills, @Bind("skillCount") int skillCount);


    @SqlQuery("select * from users")
    List<User> findAll();

    @SqlUpdate("insert into users (firstName,lastName,email,salt,password,admin,disabled) values (:firstName, :lastName, :emailAddress, :salt,:password,:admin, false)")
    void registerUser(
                      @Bind("firstName") String firstName, 
                      @Bind("lastName") String lastName,
                      @Bind("emailAddress") String emailAddress,
                      @Bind("salt") String salt,
                      @Bind("password") String password,
                      @Bind("admin") boolean admin);

    @SqlUpdate("update users set firstName = :firstName, lastName = :lastName, emailAddress = :emailAddress, title = :title, summary = :summary where username = :username")
    void updateUserDetails(@Bind("username") String username,
                      @Bind("firstName") String firstName,  
                      @Bind("lastName") String lastName, 
                      @Bind("address") String emailAddress,
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

    @SqlUpdate("delete from skills where userid = :id")
    void deleteUserSkills(@Bind("id") Long id);

    /*

    @SqlQuery("select * from users" +
            "inner join skills on users.id=skills.userid" +
            "inner join qualifications on users.id=qualifications.userid" +
            "where" +
            "users.id like :id and" +
            "users.username like :id and" +
            "users.firstname like :id and" +
            "users.lastname like :id and" +
            "users.address like :id and" +
            "users.title like :id and" +
            "users.summary like :id and" +
            "users.salt like :id and" +
            "users.password like :id and" +
            "users.admin like :id and" +
            "users.disabled like :id and" +

            "skills.id like :id and" +
            "skills.userid like :id and" +
            "skills.skill like :id and" +
            "skills.level like :id and" +

            "qualifications.id like :id and" +
            "qualifications.userid like :id and" +
            "qualifications.qualification like :id"
    )
    List<User> findByAny();

    * */

    void close();
}
