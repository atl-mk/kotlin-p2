package com.atlassian.pedagogical.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Don't want two REST resources with the same root path
@Path("/java")
public class JavaRest {

    /**
     * Take a look at
     * @see <a href="http://localhost:5990/refapp/rest/kotlin-example/1.0/java">http://localhost:5990/refapp/rest/kotlin-example/1.0/java</a>
     */
    @AnonymousAllowed
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("")
    public String helloWorld() {
        return "hey there from Java land";
    }
}
