package dw.quickstarts.views;

import dw.quickstarts.User;
import io.dropwizard.views.View;

import java.net.URI;

/**
 * Created by koskinasm on 07/10/2016.
 */
public class UserDetailsView extends View {
    private final URI logoutLocation;
    private final User user;

    public UserDetailsView(URI logoutLocation,User user) {
        super("userdetails.ftl");
        this.logoutLocation = logoutLocation;
        this.user = user;
    }

    public URI getLogoutLocation() {
        return logoutLocation;
    }

    public User getUser(){return this.user;}

}

