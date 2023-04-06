package com.example.puyuan.config;

import com.example.puyuan.auth.AuthenticationResponse;
import com.example.puyuan.base.StatusResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final StatusResponse FAILED_RESULT = StatusResponse.FAILED(); //預設為失敗狀態

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StatusResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        System.out.print(ex);
        return ResponseEntity.badRequest().body(FAILED_RESULT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StatusResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(FAILED_RESULT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<StatusResponse> handleException(Exception ex) {
        System.out.print(ex);

        return ResponseEntity.badRequest().body(FAILED_RESULT);
    }


}
