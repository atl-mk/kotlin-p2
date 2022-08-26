package com.atlassian.pedagogical.rest

import com.atlassian.plugins.rest.common.security.AnonymousAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

// Don't want two REST resources with the same root path
@Path("/kotlin")
class KotlinRest {

    /**
     * Take a look at
     * @see <a href="http://localhost:5990/refapp/rest/kotlin-example/1.0/kotlin">http://localhost:5990/refapp/rest/kotlin-example/1.0/kotlin</a>
     */
    @AnonymousAllowed
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("")
    fun helloWorld(): String {
        return "hey there from kotlin land"
    }
}