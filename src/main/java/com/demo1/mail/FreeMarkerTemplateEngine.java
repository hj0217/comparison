package com.demo1.mail;


import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreeMarkerTemplateEngine implements TemplateEngine{


    private final Configuration configuration;

    @Value("${spring.freemarker.template-loader-path}")
    private String templateLoaderPath;

    @Value("${spring.freemarker.cache}")
    private boolean cache;

    @Value("${spring.freemarker.suffix}")
    private String suffix;

    public FreeMarkerTemplateEngine(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public String processTemplate(String templatePath, Mail mail) {

        Template template = null;
        try {
            template = configuration.getTemplate(templatePath + suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> templateModel = new HashMap<>();
        String result ="";
        for (final MailReceiver receiver : mail.getReceiverList()) {
            templateModel.put("recipientName", receiver.getReceiverName());
            templateModel.put("contents", mail.getContent());

            try {
                result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
                return result;
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public void setTemplateLoaderPath(String templateLoaderPath) {
        this.templateLoaderPath = templateLoaderPath;
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), templateLoaderPath));
    }

    public void setCache(boolean cache) {
        this.cache = cache;
        configuration.setCacheStorage(new NullCacheStorage()); // Disables cache
    }
}
