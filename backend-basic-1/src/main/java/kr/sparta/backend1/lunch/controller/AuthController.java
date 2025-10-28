package kr.sparta.backend1.lunch.controller;

import kr.sparta.backend1.lunch.dto.BaseResponse;
import kr.sparta.backend1.lunch.dto.MemberDto;
import kr.sparta.backend1.lunch.dto.MemberResponseDto;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.jwt.JwtTokenProvider;
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


    //@Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<MemberResponseDto>> signup(@Validated @RequestBody MemberDto dto) {
        Member member = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created(
                        new MemberResponseDto(member.getUsername(),
                                member.getPassword())));
    }


    //@Operation(summary = "로그인", description = "회원 로그인 후 JWT 토큰을 발급받습니다.")
    @PostMapping("/login")
//    public ResponseEntity<BaseResponse<Map<String, String>>> login(
    public ResponseEntity<BaseResponse<?>> login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        Member member = memberService.getMemberByUsername(username);

        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            String token = jwtTokenProvider.createToken(member.getUsername(), member.getRole());
            return ResponseEntity.ok(BaseResponse.success(Map.of("accessToken", token)));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(BaseResponse.error("아이디 또는 비밀번호 오류"));
    }

}