package com.atlassian.pedagogical.frontend.servlet;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.soy.renderer.SoyException;
import com.atlassian.soy.renderer.SoyTemplateRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.util.Collections.emptyMap;

public class JavaServlet extends HttpServlet {

    private final SoyTemplateRenderer soyTemplateRenderer;

    public JavaServlet(@ComponentImport final SoyTemplateRenderer soyTemplateRenderer) {
        this.soyTemplateRenderer = soyTemplateRenderer;
    }

    private static final String ENCODING = StandardCharsets.UTF_8.name();
    private static final String PLUGIN_KEY = "com.atlassian.pedagogical.kotlin-p2";
    private static final String RESOURCE_KEY = ":kotlin-base-resources";
    private static final String TEMPLATE_KEY = "atlassian.kotlin.kotlinBase";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=" + ENCODING);

        try {
            soyTemplateRenderer.render(resp.getWriter(), PLUGIN_KEY + RESOURCE_KEY, TEMPLATE_KEY, emptyMap());
        } catch (SoyException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw (IOException) cause;
            }
            throw new ServletException(e);
        }
    }
}