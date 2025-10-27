package kr.sparta.backend1.lunch.controller;

import kr.sparta.backend1.lunch.dto.BaseResponse;
import kr.sparta.backend1.lunch.dto.MemberDto;
import kr.sparta.backend1.lunch.dto.MemberResponseDto;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.service.MemberService;
import lombok.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "인증 API", description = "회원가입 및 로그인 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    //@Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<MemberResponseDto>> signup(@Validated @RequestBody MemberDto dto) {
        Member member = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created(
                        new MemberResponseDto(member.getUsername(),
                                member.getPassword())));
    }

}