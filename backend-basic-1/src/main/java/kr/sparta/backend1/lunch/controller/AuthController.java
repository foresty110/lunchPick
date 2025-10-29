package kr.sparta.backend1.lunch.controller;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.sparta.backend1.lunch.dto.*;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.jwt.JwtTokenProvider;
import kr.sparta.backend1.lunch.jwt.RefreshTokenStore;
import kr.sparta.backend1.lunch.service.MemberService;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Tag(name = "인증 API", description = "회원가입 및 로그인 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenStore refreshTokenStore;

    //@Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<SignUpResponseDto>> signup(@Validated @RequestBody SignUpRequestDto dto) {

        Member member = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created(
                        new SignUpResponseDto(member.getId(),
                                member.getEmail(),
                                member.getName())));
    }


    //@Operation(summary = "로그인", description = "회원 로그인 후 JWT 토큰을 발급받습니다.")
    @PostMapping("/login")
   public ResponseEntity<BaseResponse<Map<String, String>>> login(@Valid @RequestBody LoginRequestDto request, HttpServletResponse response) {
        Member member = memberService.getMemberByEmail(request.getEmail());

        if (member != null && passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            String accessToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole());
            String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail());

            //세션 저장
            jwtTokenProvider.addJwtToCookie(accessToken, response);

            // Refresh Token 저장
            refreshTokenStore.save(member.getEmail(), refreshToken);

            return ResponseEntity.ok(BaseResponse.success(Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            )));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BaseResponse<>(401, "아이디 또는 비밀번호 오류", null));
    }


    //@Operation(summary = "Access Token 재발급", description = "Refresh Token을 사용해 새로운 Access Token을 발급합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<Map<String, String>>> refreshToken(@RequestHeader("Authorization") String bearerToken) {
        String refreshToken = bearerToken.replace("Bearer ", "");

        try {
            jwtTokenProvider.assertValid(refreshToken); // 데이터 꺼내기
            String username = jwtTokenProvider.getUsername(refreshToken); // 유저 이름 꺼내오기

            if (!refreshTokenStore.isValid(username, refreshToken)) { // 저장되어 있는 값과 동일한지 확인하기
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                       .body(new BaseResponse<>(401, "유효하지 않은 Refresh Token", null));
            }

            // 새로운 access token 생성하기
            Member member = memberService.getMemberByEmail(username);
            String newAccessToken = jwtTokenProvider.createToken(username, member.getRole());
            return ResponseEntity.ok(BaseResponse.success(Map.of(
                    "accessToken", newAccessToken
            )));

        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new BaseResponse<>(401, "Refresh Token이 유효하지 않습니다.", null));
        }
    }
}
