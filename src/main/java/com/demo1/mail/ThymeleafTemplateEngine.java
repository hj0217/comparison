package com.demo1.mail;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.util.HashMap;
import java.util.Map;

@Component
public class ThymeleafTemplateEngine implements TemplateEngine{

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public String processTemplate(String templatePath, Mail mail ) {
        Map<String, Object> templateModel = new HashMap<>();
        for (final MailReceiver receiver : mail.getReceiverList()) {
            templateModel.put("recipientName", receiver.getReceiverName());
        }
        templateModel.put("contents", mail.getContent());

        Context context = new Context();
        context.setVariables(templateModel);
        return templateEngine.process(templatePath,context);
    }
}
