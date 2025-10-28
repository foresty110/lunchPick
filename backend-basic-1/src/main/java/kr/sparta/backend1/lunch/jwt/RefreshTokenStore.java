package kr.sparta.backend1.lunch.jwt;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {

    /**
     * RefreshToken은 주로 Redis등의 저장소를 사용하여  저장합니다.
     */
    private final Map<String, String> refreshTokens = new ConcurrentHashMap<>();

    public void save(String username, String refreshToken) {
        refreshTokens.put(username, refreshToken);
    }

    public boolean isValid(String username, String refreshToken) {
        return refreshToken.equals(refreshTokens.get(username));
    }

    public void remove(String username) {
        refreshTokens.remove(username);
    }
}
