package com.codecool.todo_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/signin").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET, "/api/v1/todo/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.POST, "/api/v1/todo/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.PUT, "/api/v1/todo/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.DELETE, "/api/v1/removeAllCompleted/").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .anyRequest().denyAll(); // anything else is denied
    }
}
