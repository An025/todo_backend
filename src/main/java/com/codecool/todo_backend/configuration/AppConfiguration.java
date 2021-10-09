package com.codecool.todo_backend.configuration;

import com.codecool.todo_backend.model.AppUser;
import com.codecool.todo_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfiguration {


    private final PasswordEncoder passwordEncoder;

    public AppConfiguration() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Profile("production")
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args ->{
            AppUser test_user = AppUser.builder()
                    .username("test_user")
                    .email("test@test.com")
                    .password(passwordEncoder.encode("password"))
                    .build();
            userRepository.save(test_user);
        };
    }
}
