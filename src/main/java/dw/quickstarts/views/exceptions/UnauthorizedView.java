package dw.quickstarts.views.exceptions;

import io.dropwizard.views.View;

import java.net.URI;

/**
 * Created by koskinasm on 06/10/2016.
 */
public class UnauthorizedView extends View {
    private final URI homeLocation;
    private final URI logoutLocation;

    public UnauthorizedView(URI homeLocation, URI logoutLocation) {
        super("unauthorizedview.ftl");
        this.homeLocation = homeLocation;
        this.logoutLocation = logoutLocation;
    }

    public URI geHomeLocation() {
        return homeLocation;
    }

    public URI getLogoutLocation() {
        return logoutLocation;
    }
}
