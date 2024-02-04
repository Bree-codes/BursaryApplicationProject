package com.bree.springproject.onlinebursaryapplication.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class CommunicationService {

    @Autowired
    JavaMailSender javaMailSender;


    public void sendVerificationEmails(final String url, final String userEmail) throws MessagingException {
        String from = "Bursary-Made-Easy";
        String subject = "Verify Email";
        String body = "<p>Click here to verify your email</p>";
        body += "<h2><a href="+ url +">Verify Email</a></h2>";

        body +="Thank your";

        MimeMessage mimeMessage= javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);


        messageHelper.addTo(userEmail);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setSentDate(new Date());
        messageHelper.setText(body, true);


        javaMailSender.send(mimeMessage);
    }
}
