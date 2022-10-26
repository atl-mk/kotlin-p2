package com.atlassian.pedagogical.rest

import com.atlassian.pedagogical.ao.dao.SampleEntityDao
import com.atlassian.pedagogical.rest.model.SettingsBodyJson
import com.atlassian.plugins.rest.common.security.AnonymousAllowed
import com.atlassian.sal.api.ApplicationProperties
import com.atlassian.sal.api.pluginsettings.PluginSettings
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.fasterxml.jackson.databind.ObjectMapper
import java.text.SimpleDateFormat
import java.util.Date
import javax.ws.rs.*
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import kotlin.random.Random


// Don't want two REST resources with the same root path
@Path("/kotlin")
class KotlinRest constructor(
    private val applicationProperties: ApplicationProperties,
    private val pluginSettingsFactory: PluginSettingsFactory,
    private val objectMapper: ObjectMapper,
    private val sampleEntityDao: SampleEntityDao
) {
    private val pluginSettings: PluginSettings = pluginSettingsFactory.createGlobalSettings()
    private val PLUGIN_SETTINGS_KEY = "com.atlassian.pedagogical:plugin-settings-key"

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

    /**
     * Take a look at /src/resources/HTTPRequests.http to try it out
     */
    @GET
    @Path("setting")
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    fun getSetting(): Response {
        val settingsBodyMap = pluginSettings.get(PLUGIN_SETTINGS_KEY)
        val settingsBody = objectMapper.convertValue(settingsBodyMap, SettingsBodyJson::class.java)
        return Response.ok(settingsBody).build()
    }

    /**
     * Take a look at /src/resources/HTTPRequests.http to try it out
     */
    @POST
    @Path("setting")
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun setSetting(body: SettingsBodyJson): Response? {
        val bodyMap = objectMapper.convertValue(body, Map::class.java)
        pluginSettings.put(PLUGIN_SETTINGS_KEY, bodyMap)
        return Response.ok().build()
    }
}
