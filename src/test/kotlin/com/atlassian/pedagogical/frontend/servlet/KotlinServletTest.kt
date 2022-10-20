package com.atlassian.pedagogical.frontend.servlet

import com.atlassian.soy.renderer.SoyTemplateRenderer
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ExtendWith(MockKExtension::class)
internal class KotlinServletTest {

    @MockK
    lateinit var request: HttpServletRequest

    @RelaxedMockK
    lateinit var response: HttpServletResponse

    @RelaxedMockK
    lateinit var soyTemplateRenderer: SoyTemplateRenderer

    @InjectMockKs
    lateinit var kotlinServlet: KotlinServlet

    @Test
    fun testHtmlContentType() {
        kotlinServlet.doGet(request, response)
        verify { response.contentType = "text/html;charset=UTF-8" }
    }

    @Test
    fun testSoyTemplateRenderIsCalledWithModuleKey() {
        val moduleKey = "com.atlassian.pedagogical.kotlin-p2:kotlin-base-resources"
        val templateKey = "atlassian.kotlin.kotlinBase"

        kotlinServlet.doGet(request, response)
        verify(exactly = 1) { soyTemplateRenderer.render(any(), moduleKey, templateKey, any()) }
    }
}