package com.atlassian.pedagogical

import com.atlassian.pedagogical.util.getBaseUrl
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class DataProviderTest {
    @Test
    fun `Test Java data provider`() {
        RestAssured
                .given()
                .body(WebResourceV1RequestBody(
                        emptyList(),
                        listOf("com.atlassian.pedagogical.kotlin-p2:java-data-provider-web-resource"),
                        emptyList(),
                        emptyList()))
                .contentType(JSON)
                .`when`()
                .post(getBaseUrl() + "rest/webResources/1.0/resources")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .body("unparsedData", equalTo(
                        mapOf(Pair("com.atlassian.pedagogical.kotlin-p2:java-data-provider-web-resource.java-data-provider",
                                "{\"key\":\"value\"}"))))
    }

    @Test
    fun `Test Kotlin data provider`() {
        RestAssured
                .given()
                .body(WebResourceV1RequestBody(
                        emptyList(),
                        listOf("com.atlassian.pedagogical.kotlin-p2:kotlin-data-provider-web-resource"),
                        emptyList(),
                        emptyList()))
                .contentType(JSON)
                .`when`()
                .post(getBaseUrl() + "rest/webResources/1.0/resources")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .body("unparsedData", equalTo(
                        mapOf(Pair("com.atlassian.pedagogical.kotlin-p2:kotlin-data-provider-web-resource.kotlin-data-provider",
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