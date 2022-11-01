package com.atlassian.pedagogical.service

import com.atlassian.pedagogical.rest.model.SettingsBodyJson
import com.atlassian.sal.api.pluginsettings.PluginSettings
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.fasterxml.jackson.databind.ObjectMapper


class DefaultPluginSettingsService constructor(
    pluginSettingsFactory: PluginSettingsFactory,
    private val objectMapper: ObjectMapper
) : PluginSettingsService {

    private val pluginSettings: PluginSettings = pluginSettingsFactory.createGlobalSettings()

    override fun get(): SettingsBodyJson? {
        val settings = pluginSettings.get(PLUGIN_SETTINGS_KEY)
        return objectMapper.convertValue(settings, SettingsBodyJson::class.java)
    }

    override fun save(body: SettingsBodyJson) {
        val bodyMap = objectMapper.convertValue(body, Map::class.java)
        pluginSettings.put(PLUGIN_SETTINGS_KEY, bodyMap)
    }

    companion object {
        private const val PLUGIN_SETTINGS_KEY = "com.atlassian.pedagogical:plugin-settings-key"
    }

}