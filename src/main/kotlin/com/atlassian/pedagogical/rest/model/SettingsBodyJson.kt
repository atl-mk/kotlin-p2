package com.atlassian.pedagogical.rest.model

import org.codehaus.jackson.annotate.JsonAutoDetect
import org.codehaus.jackson.annotate.JsonProperty

@JsonAutoDetect
data class SettingsBodyJson (
    @JsonProperty("word") val word: String,
    @JsonProperty("otherword") val otherWord: String,
)

