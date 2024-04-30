package com.demo1.mail;


import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/email")
public class MailController {


    @Autowired
    private Sendable sendable;


    @PostMapping("sendMail")
    public void emailSend(@RequestParam String title, @RequestParam String content) throws MessagingException, IOException, TemplateException {

        /*
        * test 데이터
        */
        List<MailReceiver> list = new ArrayList<>();
        list.add(MailReceiver.builder().receiverName("짝붱").receiverEmail("@naver.com").build());
        list.add(MailReceiver.builder().receiverName("큰붱").receiverEmail("@naver.com").build());
        /*
        *
        */

        MailBuilder mailBuilder = new MailBuilder(title, content);
        mailBuilder.receivers(list);
        sendable.sendEmail(mailBuilder.build());
    }



//    @PostMapping("/sendMail")
//    public void emailBuilder(Template templateContent, List<MailDto> list) throws MessagingException {
//
//        for (MailDto dto : list) {
//            Map<String, Object> templateModel = new HashMap<>();
//            templateModel.put("recipientName", dto.getName());
//            templateModel.put("contents", templateContent.getContents());
//            templateModel.put("senderName", templateContent.getSenderName());
//
//
//            //템플릿에 전달할 데이터 설정
//            Context context = new Context();
//            context.setVariables(templateModel);
//
//            //템플릿 프로
//            String htmlBody = templateEngine.process("mail/email",context);;
//
//
//            //메일 보내기
//            //sendHtmlMessage(dto.getAddress(), template().getSubject(), htmlBody);
//        }
//    }



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
