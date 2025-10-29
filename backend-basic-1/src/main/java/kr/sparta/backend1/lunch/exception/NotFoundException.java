package kr.sparta.backend1.lunch.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}