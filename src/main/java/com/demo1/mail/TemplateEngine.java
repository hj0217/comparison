package com.demo1.mail;

import freemarker.template.TemplateException;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.Map;

public interface TemplateEngine {

    String processTemplate(String templatePath, Mail mail) throws IOException, TemplateException;

}

