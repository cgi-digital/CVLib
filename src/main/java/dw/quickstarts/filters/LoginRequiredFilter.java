package dw.quickstarts.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;


public class LoginRequiredFilter implements ContainerRequestFilter {
    private final HttpServletRequest webRequest;

    public LoginRequiredFilter(HttpServletRequest webRequest) {
        this.webRequest = webRequest;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final HttpSession session = webRequest.getSession();

        if (session.getAttribute("authenticated") == null ||
                !((boolean) session.getAttribute("authenticated"))) {
            throw new ForbiddenException();
        }
    }
}
