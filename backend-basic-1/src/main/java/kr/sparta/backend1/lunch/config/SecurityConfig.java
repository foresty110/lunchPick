package kr.sparta.backend1.lunch.config;
import jakarta.servlet.http.HttpServletResponse;
import kr.sparta.backend1.lunch.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(f -> f.disable())
                .httpBasic(b -> b.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products/new", "/api/products/**/edit").hasRole("ADMIN")
                        .requestMatchers("/api/member/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) -> {
                            if (req.getRequestURI().startsWith("/api/")) {
                                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                res.setContentType("application/json;charset=UTF-8");
                                res.getWriter().write("{\"status\":\"error\",\"message\":\"인증이 필요합니다.\"}");
                            } else {
                                res.sendRedirect("/api/auth/refresh");
                            }
                        })
                        .accessDeniedHandler((req, res, e) -> {
                            if (req.getRequestURI().startsWith("/api/")) {
                                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                res.setContentType("application/json;charset=UTF-8");
                                res.getWriter().write("{\"status\":\"error\",\"message\":\"접근 권한이 없습니다.\"}");
                            } else {
                                res.sendError(HttpServletResponse.SC_FORBIDDEN);
                            }
                        })
                )
                .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}