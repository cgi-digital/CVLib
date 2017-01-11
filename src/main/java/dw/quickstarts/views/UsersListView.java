package dw.quickstarts.views;

import dw.quickstarts.User;
import io.dropwizard.views.View;

import java.net.URI;
import java.util.List;

public class UsersListView extends View {

    private final URI logoutLocation;
    private List<User> users;

    public UsersListView(List<User> users, URI logoutLocation) {
        super("userslistview.ftl");
        this.users = users;
        this.logoutLocation= logoutLocation;
    }

    public List<User> getUsers() {
        return users;
    }


    public URI getLogoutLocation() {
        return logoutLocation;
    }
}
