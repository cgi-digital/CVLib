package dw.quickstarts.views.exceptions;

import io.dropwizard.views.View;

import java.net.URI;

public class ForbiddenView extends View {
    private final URI loginLocation;

    public ForbiddenView(URI loginLocation) {
        super("forbiddenview.ftl");
        this.loginLocation = loginLocation;
    }

    public URI getLoginLocation() {
        return loginLocation;
    }
}
