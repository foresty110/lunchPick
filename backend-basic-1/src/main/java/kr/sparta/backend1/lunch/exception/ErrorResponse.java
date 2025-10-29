package kr.sparta.backend1.lunch.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private int status;                 // 400, 404, 409, 500...
    private String error;               // "Bad Request", "Not Found" ...
    private String message;             // 사람이 읽을 수 있는 메시지
    private String path;                // 요청 URL
    private LocalDateTime timestamp;    // 발생 시각
    private List<FieldErrorItem> fieldErrors; // 검증 실패시 필드별 오류 목록
}