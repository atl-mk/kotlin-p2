package it.com.atlassian.pedagogical.wired

import com.atlassian.pedagogical.util.getBaseUrl
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class DataProviderTest {
    private val pluginKey = "com.atlassian.pedagogical.kotlin-p2"
    private val webResourceUrl = "rest/webResources/1.0/resources"

    @Test
    fun `Test Java data provider`() {
        RestAssured
                .given()
                .body(
                    WebResourceV1RequestBody(
                        emptyList(),
                        listOf("$pluginKey:java-data-provider-web-resource"),
                        emptyList(),
                        emptyList())
                )
                .contentType(JSON)
                // This is a private API that is subject to change, use at your own peril!
                .post(getBaseUrl() + webResourceUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .body("unparsedData", equalTo(
                        mapOf(Pair("$pluginKey:java-data-provider-web-resource.java-data-provider",
                                "{\"key\":\"value\"}"))))
    }

    @Test
    fun `Test Kotlin data provider`() {
        RestAssured
                .given()
                .body(
                    WebResourceV1RequestBody(
                        emptyList(),
                        listOf("$pluginKey:kotlin-data-provider-web-resource"),
                        emptyList(),
                        emptyList())
                )
                .contentType(JSON)
                // This is a private API that is subject to change, use at your own peril!
                .post(getBaseUrl() + webResourceUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .body("unparsedData", equalTo(
                        mapOf(Pair("$pluginKey:kotlin-data-provider-web-resource.kotlin-data-provider",
                                "{\"key\":\"value\"}"))))
    }

    /**
     * @param c web-resource contexts to be requested
     * @param r web-resources to be requested
     * @param xc web-resource contexts to be excluded (this is normally what's already loaded on the page)
     * @param xr web-resources to be excluded (this is normally what's already loaded on the page)
     */
    data class WebResourceV1RequestBody(val c: List<String>, val r: List<String>, val xc: List<String>, val xr: List<String>)
}