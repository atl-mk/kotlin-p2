package com.atlassian.pedagogical.rest

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.atlassian.plugins.rest.common.security.AnonymousAllowed
import com.atlassian.sal.api.ApplicationProperties
import com.atlassian.sal.api.pluginsettings.PluginSettings
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
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

    @GET
    @Path("setting")
    @AnonymousAllowed
    @Produces(MediaType.TEXT_PLAIN)
    fun getSetting(): String {
        return pluginSettings.get(PLUGIN_SETTINGS_KEY).toString()
    }

    @POST
    @Path("setting")
    @AnonymousAllowed
    @Produces(MediaType.TEXT_PLAIN)
    fun setSetting(@QueryParam("word") word: String) : Response? {
        pluginSettings.put(PLUGIN_SETTINGS_KEY, word)
        return Response.ok(word).build()
    }
}
