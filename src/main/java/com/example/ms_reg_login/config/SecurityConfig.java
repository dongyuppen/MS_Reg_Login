package com.example.ms_reg_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/join", "/login", "/").permitAll() // "/" 경로도 누구나 접근 가능하게 임시 추가
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        // 👇 로그인 실패 시 HTML 리다이렉트 대신 401 에러와 메시지를 반환하도록 핸들러 추가
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(401); // 401 Unauthorized 상태 코드 세팅
                            response.setCharacterEncoding("UTF-8");
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().write("아이디 또는 비밀번호가 틀렸습니다.");
                        })
                );

        return http.build();
    }
}