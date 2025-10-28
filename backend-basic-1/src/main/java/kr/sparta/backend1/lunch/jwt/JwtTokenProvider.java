package kr.sparta.backend1.lunch.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {

    private final Key secretKey;
    private final long validityMs = 30 * 60 * 1000; // 30분
    private final JwtParser parser;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) { // 32B 이상
        //고정 비밀키 준비하기
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        //파서 준비하기 (jwt로 만들어진 것의 데이터를 분리해서 얻어내는것)
        this.parser = Jwts.parser()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds(30) // 시계 오차 허용
                .build();
    }

    // JWT  생성
    // role은 접두어 없이 저장(예: "ADMIN"), 권한 접두어는 Security 쪽에서 처리
    //username과 role을 전달하면 이것을 가지고 토큰을 만드는 매서드
    public String createToken(String username, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()   // 토큰 생성하기
                .setSubject(username) // 토큰 데이터 채우기
                .claim("role", role)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + validityMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 유효하지 않으면 ExpiredJwtException/JwtException 등 throw */
    public void assertValid(String token) {
        //유효한 토큰인지 검증
        parser.parseClaimsJws(token);
    }

    // 토큰으로 username을 뽑는 매서드
    public String getUsername(String token) {
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        Object v = parser.parseClaimsJws(token).getBody().get("role");
        return v == null ? null : v.toString();
    }
}