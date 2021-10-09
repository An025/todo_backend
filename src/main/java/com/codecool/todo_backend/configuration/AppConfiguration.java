package com.codecool.todo_backend.configuration;

import com.codecool.todo_backend.model.AppUser;
import com.codecool.todo_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfiguration {

    @Bean
    @Profile("production")
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args ->{
            AppUser test_user = AppUser.builder()
                    .username("test_user")
                    .email("test@test.com")
                    .password("password")
                    .build();
            userRepository.save(test_user);
        };
    }
}
