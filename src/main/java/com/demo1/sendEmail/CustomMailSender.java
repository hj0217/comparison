package com.demo1.sendEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class CustomMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("/sendMail")
    public void sendMail(@RequestParam String subject, @RequestParam String[] to
                         //, List<Map<String, Object>> list
                         ) throws MessagingException, IOException {

        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject(subject, "utf-8");

        // Test -> Param List으로 받기
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("recipientName", "Mike");
        templateModel.put("text", "This is the contents area. Please leave it as blank! 한글 끼워넣기");
        templateModel.put("senderName", "Eugene Choi");
        System.out.println("이메일 확인용");

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariables(templateModel);

        //템플릿 프로세스
        String htmlBody = templateEngine.process("mail/email",context);;

        //메일 보내기
        sendHtmlMessage(to[0], subject, htmlBody);
    }


    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to); //수신인  설정 (setTo 파라미터에 문자열 리스트를 넘기면 한번에 여러명에게 전송 가능)
        helper.setSubject(subject); //메일 제목 설정
        helper.setText(htmlBody, true); //body 내용 설정
        javaMailSender.send(message);
    }
}
