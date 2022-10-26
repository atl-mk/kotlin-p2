package com.atlassian.pedagogical.service

import com.atlassian.pedagogical.rest.model.SettingsBodyJson

interface PluginSettingsService {

    fun get(): SettingsBodyJson?

    fun save(body: SettingsBodyJson)
}