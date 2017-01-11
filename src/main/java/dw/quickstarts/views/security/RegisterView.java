package dw.quickstarts.views.security;


import io.dropwizard.views.View;

import java.net.URI;

public class RegisterView extends View {

    private final String errorMessage;
    private final URI registerLocation;
    private final URI loginLocation;

    public RegisterView(URI loginLocation, URI registerLocation, String errorMessage) {
        super("registerview.ftl");
        this.registerLocation = registerLocation;
        this.loginLocation = loginLocation;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public URI getLoginLocation() {
        return loginLocation;
    }

    public URI getRegisterLocation() {
        return registerLocation;
    }

}
