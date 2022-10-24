package com.atlassian.pedagogical.rest

import com.atlassian.pedagogical.rest.model.SettingsBodyJson
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.atlassian.plugins.rest.common.security.AnonymousAllowed
import com.atlassian.sal.api.ApplicationProperties
import com.atlassian.sal.api.pluginsettings.PluginSettings
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


// Don't want two REST resources with the same root path
@Path("/kotlin")
class KotlinRest @Autowired constructor(
    @ComponentImport private val applicationProperties: ApplicationProperties,
    @ComponentImport private val pluginSettingsFactory: PluginSettingsFactory
) {
    val pluginSettings: PluginSettings = pluginSettingsFactory.createGlobalSettings()
    val PLUGIN_SETTINGS_KEY = "com.atlassian.pedagogical:plugin-settings-key"
    val DEFAULT_JACKSON_MAPPER = ObjectMapper().registerKotlinModule()

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
    fun helloWorld(): String {
        return "buildNumber: ${applicationProperties.buildNumber}, version: ${applicationProperties.version}"
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
        val settingsBody = DEFAULT_JACKSON_MAPPER.convertValue(settingsBodyMap, SettingsBodyJson::class.java)
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
    fun setSetting(body: SettingsBodyJson) : Response? {
        // Interesting way to convert the SettingsJsonBody class to a map. Could just make a utility class to do this. Is there
        // a way that is cleaner?
        val bodyMap = DEFAULT_JACKSON_MAPPER.convertValue(body, Map::class.java)
        pluginSettings.put(PLUGIN_SETTINGS_KEY, bodyMap)
        return Response.ok().build()
    }
}
