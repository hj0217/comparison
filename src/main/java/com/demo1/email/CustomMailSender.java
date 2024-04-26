package com.demo1.email;

import com.demo1.email.emailDto.MailDto;
import com.demo1.email.emailDto.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class CustomMailSender {


    @Autowired
    private SpringTemplateEngine templateEngine;



    // test 발신인 리스트
    private List<MailDto> list () {
        List<MailDto> list = new ArrayList<>();
        list.add(MailDto.builder()
                .address("youakdl@naver.com")
                .name("이호갱")
                .build());
        list.add(MailDto.builder()
                .address("youakdl1@naver.com")
                .name("주복치")
                .build());
        return  list;
    }

    // test 메일내용
    private Template template() {
        return Template.builder()
                .senderName("김이니")
                .contents("This is the contents area. Please leave it as blank! <br> 이곳은 내용이 들어가는 공간입니다. 공백으로 남주세요.<br>")
                .subject("제목 입력란")
                .build();
    }


    @PostMapping("/sendMail")
    public void emailBuilder(Template templateContent, List<MailDto> list) throws MessagingException {

        for (MailDto dto : list()) {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("recipientName", dto.getName());
            templateModel.put("contents", templateContent.getContents());
            templateModel.put("senderName", templateContent.getSenderName());


            //템플릿에 전달할 데이터 설정
            Context context = new Context();
            context.setVariables(templateModel);

            //템플릿 프로세스
            String htmlBody = templateEngine.process("mail/email",context);;


            //메일 보내기
            //sendHtmlMessage(dto.getAddress(), template().getSubject(), htmlBody);
        }
    }


    // javaSender
//    @Autowired
//    private JavaMailSender sender;
//
//    MimeMessage message = sender.createMimeMessage();
//
//    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//        helper.setTo(to); //수신인  설정 (setTo 파라미터에 문자열 리스트를 넘기면 한번에 여러명에게 전송 가능)
//        helper.setSubject(subject); //메일 제목 설정
//        helper.setText(htmlBody, true); //body 내용 설정
//        sender.send(message);
//    }


}
