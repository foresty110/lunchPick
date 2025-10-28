package kr.sparta.backend1.lunch.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 토큰 생성&파싱 처리하는 클래스
    private final JwtTokenProvider tokens;

    public JwtAuthenticationFilter(JwtTokenProvider tokens) {
        this.tokens = tokens;
    }

    @Override
    //request,response를 받아오는 중간필터이다.
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String h = req.getHeader("Authorization");
        String token = (h != null && h.startsWith("Bearer ")) ? h.substring(7) : null;

        if (token != null) {
            try {
                tokens.assertValid(token);//tokens는 header,payload,signature로 분리된 토큰들을 말한다.
                String username = tokens.getUsername(token);
                String role = tokens.getRole(token); // 예: "ADMIN"

                // 권한 부여
                Collection<? extends GrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                //SecurityContext안에 권한을 저장한다.
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (ExpiredJwtException e) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다.");
                return;
            } catch (JwtException | IllegalArgumentException e) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}