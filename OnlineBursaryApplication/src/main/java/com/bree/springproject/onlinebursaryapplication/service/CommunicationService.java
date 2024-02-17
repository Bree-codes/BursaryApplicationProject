package com.bree.springproject.onlinebursaryapplication.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Date;

@Service
@Data
@Slf4j
public class CommunicationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public CommunicationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationEmails(final String url, final String userEmail)
            throws MessagingException {
        String from = "Bursary-Made-Easy";
        String subject = "Verify Email";
        String body = """
                <!DOCTYPE html>
                                <html>
                                <head>
                                  \s
                                    <style>
                                        .container {
                                            width: 80%;
                                            height: 300px;
                                            display: flex;
                                            flex-direction: column;
                                            justify-content: center;
                                            align-items: center;
                                            background-image: url('https://images.unsplash.com/photo-1538495435388-104fd74d46a5?q=80&w=1507&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                                            background-size: cover;
                                            text-align: center;
                                            color: green;
                                        }
                                                               \s
                                        a {
                                            background-color: green;
                                            color: white;
                                            padding: 10px;
                                            border: none;
                                            cursor: pointer;
                                            font-weight: bold;
                                            text-align: center;
                                            height: 8%;
                                            width:15%;
                                            margin: 10px;
                                            margin-top: 50px;
                                            font-size: 16px;
                                            top : 50%;
                                            left:50%;
                                        }
                                    </style>
                                <body>
                                    <div class="container">
                                        <p><b>Click here to verify your email</b></p><br>
                                        <a href='"""+url+""" 
                                    '><center> Verify Email</center></a>
                                    </div>
                                </body>
                                </html>
                """;

        MimeMessage mimeMessage= javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);


        messageHelper.addTo(userEmail);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setSentDate(new Date());
        messageHelper.setText(body, true);


        javaMailSender.send(mimeMessage);
    }

    public void sendChangePasswordEmail(String userEmail, String url) throws MessagingException {

        String from = "Bursary-Made-Easy";
        String subject = "Verify Email";
        String body = """
                <!DOCTYPE html>
                                <html>
                                <head>
                                  \s
                                    <style>
                                        .container {
                                            width: 80%;
                                            height: 300px;
                                            display: flex;
                                            flex-direction: column;
                                            justify-content: center;
                                            align-items: center;
                                            background-image: url('https://images.unsplash.com/photo-1538495435388-104fd74d46a5?q=80&w=1507&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                                            background-size: cover;
                                            text-align: center;
                                            color: green;
                                        }
                                                               \s
                                        a {
                                            background-color: green;
                                            color: white;
                                            padding: 10px;
                                            border: none;
                                            cursor: pointer;
                                            font-weight: bold;
                                            text-align: center;
                                            height: 12%;
                                            width:20%;
                                            margin: 10px;
                                            margin-top: 50px;
                                            font-size: 16px;
                                            top : 50%;
                                            left:50%;
                                        }
                                    </style>
                                <body>
                                    <div class="container">
                                        <p><b>Click here to change you password</b></p><br>
                                        <a href='"""+url+"""
                                    '><center>Forgotten Password</center></a>
                                    </div>
                                </body>
                                </html>
                """;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setText(body, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(userEmail);
        messageHelper.setSubject(subject);
        messageHelper.setSentDate(new Date());


        log.info("Forgotten password sending the email");

        javaMailSender.send(mimeMessage);
    }

    public void emailPrivilegedUsers(String email, String username, String defaultPassword) {
    }
}
