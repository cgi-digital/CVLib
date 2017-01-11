package dw.quickstarts.filters;

import dw.quickstarts.dao.UserDAO;
import org.glassfish.jersey.server.model.AnnotatedMethod;
import dw.quickstarts.annotations.LoginRequired;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.FeatureContext;

public class LoginRequiredFeature implements DynamicFeature {
    @Context
    HttpServletRequest webRequest;

    @Inject
    UserDAO userDAO;

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        AnnotatedMethod am = new AnnotatedMethod(resourceInfo.getResourceMethod());
        if (resourceInfo.getResourceMethod().getAnnotation(LoginRequired.class) != null) {
            context.register(new LoginRequiredFilter(webRequest));
        }
    }
}
