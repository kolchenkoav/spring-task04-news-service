package com.example.rest.rest;


import com.example.rest.rest.model.User;
import com.example.rest.rest.web.model.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractTestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setUserName("User "+ id);
        return user;
    }

    protected UserResponse createUserResponse(Long id) {
        UserResponse userResponse = new UserResponse(
                id,
                "User " + id);

        return userResponse;
    }
}
