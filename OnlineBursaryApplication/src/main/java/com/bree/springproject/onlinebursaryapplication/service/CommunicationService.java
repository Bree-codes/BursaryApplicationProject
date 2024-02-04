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
        String body = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        .container {\n" +
                "            width: 100%;\n" +
                "            height: 300px;\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            background-image: url('https://images.unsplash.com/photo-1706713115353-2dae8015a178?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyMzR8fHxlbnwwfHx8fHw%3D');\n" +
                "            background-size: cover;\n" +
                "            text-align: center; /* Center the text */\n" +
                "            color: white; /* Set text color to white */\n" +
                "        }\n" +
                "\n" +
                "        button {\n" +
                "            background-color: blue;\n" +
                "            color: white;\n" +
                "            padding: 10px 20px;\n" +
                "            border: none;\n" +
                "            cursor: pointer;\n" +
                "            font-weight: bold;\n" +
                "            height: 30%;" +
                "            margin:10px;" +
                "            margin-top:50px;" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p><b>Click here to verify your email</b></p><br>\n" +
                "        <button onclick=\"window.location.href='https://github.com/Red-stevo'\">Verify Email</button>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

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
