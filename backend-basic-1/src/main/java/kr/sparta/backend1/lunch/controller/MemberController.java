package kr.sparta.backend1.lunch.controller;

import kr.sparta.backend1.lunch.dto.BaseResponse;
import kr.sparta.backend1.lunch.dto.MemberDto;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "회원 API", description = "회원 CRUD 관련 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //@Operation(summary = "회원 목록 조회", description = "모든 회원을 페이징으로 조회합니다.")
    @GetMapping
    public ResponseEntity<BaseResponse<Page<Member>>> getAllMembers(
            //@Parameter(description = "페이지 번호(0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            //@Parameter(description = "페이지 크기", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        var data = memberService.getAllMembers(page, size);
        return ResponseEntity.ok(BaseResponse.success(data));
    }

    //@Operation(summary = "내 정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<Member>> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // userDetails.getUsername()은 로그인 시 사용한 username을 반환
        Member member = memberService.getMemberByUsername(userDetails.getUsername());
        return ResponseEntity.ok(BaseResponse.success(member));
    }

    //@Operation(summary = "회원 상세 조회", description = "ID로 특정 회원을 조회합니다. (관리자 전용)")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Member>> getMember(
            //@Parameter(description = "회원 ID", example = "1", required = true)
            @PathVariable String username
    ) {
        var member = memberService.getMemberByUsername(username);
        return ResponseEntity.ok(BaseResponse.success(member));
    }

    //@Operation(summary = "회원 수정", description = "본인 또는 관리자만 회원 정보를 수정할 수 있습니다.")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Member>> updateMember(
           // @Parameter(description = "회원 ID", example = "1", required = true)
            @PathVariable Long id,
            @Validated @RequestBody MemberDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        var updated = memberService.updateMember(id, dto);
        return ResponseEntity.ok(BaseResponse.success(updated));
    }

   // @Operation(summary = "회원 삭제", description = "특정 회원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
            //@Parameter(description = "회원 ID", example = "1", required = true)
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
