package com.bree.springproject.onlinebursaryapplication;

import com.bree.springproject.onlinebursaryapplication.service.RegisterUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineBursaryApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testChangePassword()
    {
        RegisterUserService registerUserService = new RegisterUserService();

        registerUserService.changePassword("steve");
    }

}
