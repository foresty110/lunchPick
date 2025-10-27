package kr.sparta.backend1.lunch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    //@NotBlank(message = "아이디는 필수입니다.")
    //@Size(min = 4, max = 50, message = "아이디는 4~50자 이내여야 합니다.")
    private Long id;  // 로그인용 아이디

    //@Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;  // 회원가입 시에만 사용

    @NotBlank(message = "이름은 필수입니다.")
    //@Size(max = 60, message = "이름은 60자 이내여야 합니다.")
    private String username;
}