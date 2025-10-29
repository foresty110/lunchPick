package kr.sparta.backend1.lunch.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - JSON 파싱/본문 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest req
    ) {
        return build(BAD_REQUEST, "요청 본문이 잘못되었습니다.", req.getRequestURI(), null);
    }

    // 400 - @Valid DTO 바인딩 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {
        List<FieldErrorItem> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorItem(
                        err.getField(),
                        err.getDefaultMessage()
                ))
                .toList();

        return build(BAD_REQUEST, "입력값이 올바르지 않습니다.", req.getRequestURI(), fields);
    }

    // 400 - @Validated 파라미터 검증 오류
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraint(
            ConstraintViolationException ex,
            HttpServletRequest req
    ) {
        List<FieldErrorItem> fields = ex.getConstraintViolations()
                .stream()
                .map(v -> new FieldErrorItem(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                ))
                .toList();

        return build(BAD_REQUEST, "입력값이 올바르지 않습니다.", req.getRequestURI(), fields);
    }

    // 404 - 리소스 없음
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NotFoundException ex,
            HttpServletRequest req
    ) {
        return build(NOT_FOUND, ex.getMessage(), req.getRequestURI(), null);
    }

    // 405 - 미지원 HTTP 메서드
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest req
    ) {
        return build(METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.", req.getRequestURI(), null);
    }

    // 409 - 무결성/유니크 제약 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleConflict(
            DataIntegrityViolationException ex,
            HttpServletRequest req
    ) {
        return build(CONFLICT, "데이터 무결성 제약을 위반했습니다.", req.getRequestURI(), null);
    }

    // 400 - 폼 바인딩 오류(@ModelAttribute 등)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBind(
            BindException ex,
            HttpServletRequest req
    ) {
        List<FieldErrorItem> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorItem(
                        err.getField(),
                        err.getDefaultMessage()
                ))
                .toList();

        return build(BAD_REQUEST, "입력값이 올바르지 않습니다.", req.getRequestURI(), fields);
    }

    // 500 - 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleEtc(
            Exception ex,
            HttpServletRequest req
    ) {
        return build(INTERNAL_SERVER_ERROR, "서버에서 오류가 발생했습니다.", req.getRequestURI(), null);
    }

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String message,
            String path,
            List<FieldErrorItem> fields
    ) {
        ErrorResponse body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .timestamp(LocalDateTime.now())
                .fieldErrors(fields)
                .build();

        return status(status).body(body);
    }
}
