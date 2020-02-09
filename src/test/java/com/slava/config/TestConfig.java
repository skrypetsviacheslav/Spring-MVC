package com.slava.config;

import com.slava.service.UserService;
import com.slava.util.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }
}
