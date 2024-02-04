package com.bree.springproject.onlinebursaryapplication;

import com.bree.springproject.onlinebursaryapplication.service.CommunicationService;
import com.bree.springproject.onlinebursaryapplication.service.RegisterUserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineBursaryApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    CommunicationService service;


    @Test
    public void test() throws MessagingException {
        service.sendVerificationEmails("https://github.com/Red-stevo", "stevenmuish@gmail.com");
    }

}
