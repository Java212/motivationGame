package ru.inspired.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.inspired.model.Role;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private  UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/notes").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/markEvents").hasRole(Role.ROLE_USER)
                        .requestMatchers("/today").hasRole(Role.ROLE_USER)
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .successForwardUrl("/menu")
                        .failureForwardUrl("/login_error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/login")
                );
        return http.build();

    }



}
