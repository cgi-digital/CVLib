package dw.quickstarts.views;

import dw.quickstarts.User;
import io.dropwizard.views.View;

import java.net.URI;

public class HomeView extends View {

    private final URI adminConsoleLocation;
    private final URI logoutLocation;

    private final  User user;

    public HomeView(URI logoutLocation, URI adminConsoleLocation, User user) {
        super("homepage.ftl");
        this.logoutLocation = logoutLocation;
        this.adminConsoleLocation = adminConsoleLocation;
        this.user = user;
    }

    public URI getLogoutLocation() {
        return logoutLocation;
    }

    public URI getAdminConsoleLocation() {
        return adminConsoleLocation;
    }

    public User getUser() {
        return user;
    }
}
