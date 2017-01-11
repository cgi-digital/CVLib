package dw.quickstarts.tasks;

import dw.quickstarts.AuthenticationQuickstartConfiguration;
import dw.quickstarts.dao.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.skife.jdbi.v2.DBI;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SetUserPassword extends EnvironmentCommand<AuthenticationQuickstartConfiguration> {
    public SetUserPassword(Application<AuthenticationQuickstartConfiguration> application) {
        super(application, "setuserpassword", "Sets the user password in the db");
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);

        subparser.addArgument("-u", "--username")
                .dest("username")
                .type(String.class)
                .required(true)
                .help("Username");
        subparser.addArgument("-p", "--password")
                .dest("password")
                .type(String.class)
                .required(true)
                .help("Password");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, AuthenticationQuickstartConfiguration authenticationQuickstartConfiguration) throws Exception {
        String salt = RandomStringUtils.randomAlphanumeric(12);
        String username = namespace.getString("username");
        String password = namespace.getString("password");
        String saltedPassword = String.format("%s%s", salt, password);
        System.out.println(String.format("Salted password: %s", saltedPassword));

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        String hexHash = Hex.encodeHexString(hash);
        System.out.println(String.format("Hash of salted password: %s", hexHash));

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, authenticationQuickstartConfiguration.getDatabase(), "h2");
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);

        userDAO.updateUserCredentials(username, salt, hexHash);
        userDAO.close();
        System.out.println("Updated");

        // No idea why I have to do this but if I don't, the process hangs
        System.exit(0);
    }
}
