package dw.quickstarts.views;

import io.dropwizard.views.View;
import java.net.URI;

/**
 * Created by koskinasm on 06/10/2016.
 */
public class AdminConsoleView extends View {

    private final URI homeLocation;
    private final URI userListLocation;
    private final URI logoutLocation;
    private final Long id;

    public AdminConsoleView(URI homeLocation, URI userListLocation, URI logoutLocation, Long id) {
        super("adminconsole.ftl");
        this.homeLocation = homeLocation;
        this.userListLocation = userListLocation;
        this.logoutLocation = logoutLocation;
        this.id = id;
    }

    public URI getLogoutLocation() {
        return logoutLocation;
    }

    public URI getHomeLocation() {
        return homeLocation;
    }

    public URI getUserListLocation() {
        return userListLocation;
    }

    public Long getId(){return this.id;}
}
