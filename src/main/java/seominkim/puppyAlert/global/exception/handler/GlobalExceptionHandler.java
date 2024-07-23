package seominkim.puppyAlert.global.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import seominkim.puppyAlert.global.exception.exception.HostException;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

// 예외를 전역적으로 처리할 수 있는 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HostException.class)
    protected ResponseEntity handleHostException(HostException e){
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(PuppyException.class)
    protected ResponseEntity handlePuppyException(PuppyException e){
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
    }
}