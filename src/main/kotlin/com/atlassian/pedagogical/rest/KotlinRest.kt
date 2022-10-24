package com.atlassian.pedagogical.rest

import com.atlassian.pedagogical.ao.dao.SampleEntityDao
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.atlassian.plugins.rest.common.security.AnonymousAllowed
import com.atlassian.sal.api.ApplicationProperties
import org.springframework.beans.factory.annotation.Autowired
import java.text.SimpleDateFormat
import java.util.Date
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlin.random.Random

// Don't want two REST resources with the same root path
@Path("/kotlin")
class KotlinRest @Autowired constructor(
    @ComponentImport private val applicationProperties: ApplicationProperties,
    private val sampleEntityDao: SampleEntityDao
) {

    /**
     * Take a look at
     * @see <a href="http://localhost:5990/refapp/rest/kotlin-example/1.0/kotlin">http://localhost:5990/refapp/rest/kotlin-example/1.0/kotlin</a>
     */
    @GET
    @Path("")
    @AnonymousAllowed
    @Produces(MediaType.TEXT_PLAIN)
    fun helloWorld2(): String {
        return "hey there from kotlin land yay"
    }

    @GET
    @Path("info")
    @AnonymousAllowed
    @Produces(MediaType.TEXT_PLAIN)
    fun info(): String {
        return "buildNumber: ${applicationProperties.buildNumber}, version: ${applicationProperties.version}"
    }

    @GET
    @Path("test-ao")
    @AnonymousAllowed
    @Produces(MediaType.TEXT_PLAIN)
    fun testAo(): String {

        val randomNum = Random.nextInt(0, 999999999)

        val name = "SomeRandomName$randomNum"

        sampleEntityDao.save(name)
        val entity = sampleEntityDao.get(name)!!
        val timeFormatted = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(entity.eventTimestamp))

        return "Persisted entity => {Id: ${entity.id}, Name: ${entity.name}, Created at:${timeFormatted}}"
    }
}