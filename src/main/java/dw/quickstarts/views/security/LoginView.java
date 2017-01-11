package dw.quickstarts.views.security;


import io.dropwizard.views.View;

import java.net.URI;

public class LoginView extends View {
    private final URI loginLocation;
    private final URI registerLocation;
    private final String message;

    public LoginView(URI loginLocation, URI registerLocation, String message) {
        super("loginview.ftl");
        this.loginLocation = loginLocation;
        this.registerLocation = registerLocation;
        this.message = message;
    }

    public URI getLoginLocation() {
        return loginLocation;
    }

    public URI getRegisterLocation() {
        return registerLocation;
    }

    public String getMessage() { return message;    }
}
