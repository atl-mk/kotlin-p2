package com.atlassian.pedagogical.frontend.servlet

import com.atlassian.soy.renderer.SoyException
import com.atlassian.soy.renderer.SoyTemplateRenderer
import com.google.common.annotations.VisibleForTesting
import java.nio.charset.StandardCharsets
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class KotlinServlet constructor(
    private val soyTemplateRenderer: SoyTemplateRenderer
) : HttpServlet() {
    private val encoding = StandardCharsets.UTF_8.name()
    private val pluginKey = "com.atlassian.pedagogical.kotlin-p2"
    private val resourceKey = ":kotlin-base-resources"
    private val templateKey = "atlassian.kotlin.kotlinBase"

    @VisibleForTesting
    public override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.contentType = "text/html;charset=$encoding"

        try {
            soyTemplateRenderer.render(
                resp.writer,
                pluginKey + resourceKey,
                templateKey,
                emptyMap()
            )
        } catch (e: SoyException) {
            throw ServletException(e)
        }
    }
}