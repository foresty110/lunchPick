package kr.sparta.backend1.lunch.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// config/SecurityConfig.java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->auth.requestMatchers("/products/**").authenticated()) // 이 url 패턴을 가지면 지정한 인증 폼을 거치도록 해라
                //.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) //임시로 모두 허용
                .formLogin(withDefaults()) //로그인 폼 기본 제공으로 설정
                .csrf(csrf -> csrf.disable()); // 외부 접근 설정
        return http.build();
    }

}