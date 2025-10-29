package kr.sparta.backend1.lunch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorItem {
    private String field;    // ex) "price"
    private String reason;   // ex) "가격은 1,000원 이상이어야 합니다."
}