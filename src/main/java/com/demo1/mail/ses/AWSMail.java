package com.demo1.mail.ses;

import com.demo1.mail.Mail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.servlet.tags.MessageTag;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AWSMail extends Mail {

    private static final long serialVersionUID = -5484824717897100088L;

    private String configurationSetName;

    //private List<MessageTag> messageTags; // 임포트 aws꺼여야함.
}
