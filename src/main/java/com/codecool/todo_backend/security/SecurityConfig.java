package com.codecool.todo_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
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
                .antMatchers(HttpMethod.DELETE, "/api/v1/todo/**").authenticated() // allowed only when signed in
//                .antMatchers(HttpMethod.DELETE, "/api/v1/todo/removeAllCompleted/").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .anyRequest().denyAll() // anything else is denied
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
