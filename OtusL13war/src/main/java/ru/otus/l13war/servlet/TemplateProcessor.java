package ru.otus.l13war.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateProcessor {
    private static final String HTML_DIR = "/public_html/";
    private static TemplateProcessor instance = new TemplateProcessor();
    private final Configuration configuration;

    private TemplateProcessor() {
        configuration = new Configuration();
    }

    static TemplateProcessor instance() {
        return instance;
    }

    String getPage(String filename, Map<String, Object> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(HTML_DIR + filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    String getPage(String filename) throws IOException {
            Template template = configuration.getTemplate(HTML_DIR + filename);
            return template.toString();
    }
}
