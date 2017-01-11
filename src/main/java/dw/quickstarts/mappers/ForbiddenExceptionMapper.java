package dw.quickstarts.mappers;

import dw.quickstarts.utilities.URITools;
import dw.quickstarts.views.exceptions.ForbiddenView;
import dw.quickstarts.resources.SecurityResource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
    @Context
    UriInfo uriInfo;
    @Context
    HttpServletRequest request;

    @Override
    public Response toResponse(ForbiddenException e) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity(
                        uriInfo.getBaseUriBuilder()
                                .path(SecurityResource.class)
                                .path("/login")
                                .build()
                        //URITools.buildURI(uriInfo,SecurityResource.class,"/login")
                ).build();
    }
}
