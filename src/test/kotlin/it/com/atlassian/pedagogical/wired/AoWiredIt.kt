package it.com.atlassian.pedagogical.wired

import com.atlassian.pedagogical.rest.model.SettingsBodyJson
import com.atlassian.pedagogical.service.PluginSettingsService
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner
import com.atlassian.sal.api.pluginsettings.PluginSettings
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AtlassianPluginsTestRunner::class)
class AoWiredIT constructor(
    pluginSettingsFactory: PluginSettingsFactory,
    private val pluginSettingsService: PluginSettingsService
) {

    private val pluginSettings: PluginSettings = pluginSettingsFactory.createGlobalSettings()

    @Test
    fun `PluginSettingsService should persists plugin settings correctly`() {
        pluginSettingsService.save(SettingsBodyJson("first word", "Second word"))
        val persistedSettings = pluginSettings.get("com.atlassian.pedagogical:plugin-settings-key") as Map<*, *>

        assertEquals("first word", persistedSettings["word"])
        assertEquals("Second word", persistedSettings["otherWord"])
    }
}
