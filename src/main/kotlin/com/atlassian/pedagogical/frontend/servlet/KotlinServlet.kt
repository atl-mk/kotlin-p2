package com.atlassian.pedagogical.frontend.servlet

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.atlassian.soy.renderer.SoyException
import com.atlassian.soy.renderer.SoyTemplateRenderer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class KotlinServlet @Autowired constructor(
    @ComponentImport private val soyTemplateRenderer: SoyTemplateRenderer
) : HttpServlet() {
    private val encoding = StandardCharsets.UTF_8.name()
    private val pluginKey = "com.atlassian.pedagogical.kotlin-p2"
    private val resourceKey = ":kotlin-base-resources"
    private val templateKey = "atlassian.kotlin.kotlinBase"

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.setContentType("text/html;charset=" + encoding)

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