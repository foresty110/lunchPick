package kr.sparta.backend1.lunch.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;

    public BaseResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "요청 성공", data);
    }

    public static <T> BaseResponse<T> created(T data) {
        return new BaseResponse<>(201, "리소스 생성 성공", data);
    }

    public static BaseResponse<?> error(String message) {
        return new BaseResponse<>(400, message, null);
    }
}
