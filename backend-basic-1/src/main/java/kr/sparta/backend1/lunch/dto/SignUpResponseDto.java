package kr.sparta.backend1.lunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {

    private Long id; // 고유ID
    private String email; // 이메일
    private String name; //이름

}