package dw.quickstarts.utilities;

import dw.quickstarts.resources.SecurityResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by koskinasm on 07/10/2016.
 */
public class URITools {

    public static final URI buildURI(UriInfo uriInfo, Class resourceClass, String path)
    {
        return uriInfo
                .getBaseUriBuilder()
                .path(resourceClass)
                .path(path)
                .scheme(null)
                .build();
    }
}
