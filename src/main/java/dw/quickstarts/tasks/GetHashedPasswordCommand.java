package dw.quickstarts.tasks;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class GetHashedPasswordCommand extends Command {

    public GetHashedPasswordCommand() {
        super("gethashedpassword", "Encodes a salt and password");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser.addArgument("-s", "--salt")
                .dest("salt")
                .type(String.class)
                .required(true)
                .help("The salt for the password");
        subparser.addArgument("-p", "--password")
                .dest("password")
                .type(String.class)
                .required(true)
                .help("The password");
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        String salt = namespace.getString("salt");
        String password = namespace.getString("password");
        String saltedPassword = String.format("%s%s", salt, password);
        System.out.println(saltedPassword);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        String hexHash = Hex.encodeHexString(hash);
        System.out.println(hexHash);
    }
}
