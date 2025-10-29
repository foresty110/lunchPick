package kr.sparta.backend1.lunch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "아이디는 필수입니다.")
    @Email
    String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    String password;
}
