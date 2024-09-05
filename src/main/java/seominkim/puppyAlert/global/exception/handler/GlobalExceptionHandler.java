package seominkim.puppyAlert.global.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import seominkim.puppyAlert.global.exception.exception.CommonException;
import seominkim.puppyAlert.global.exception.exception.FoodException;
import seominkim.puppyAlert.global.exception.exception.UserException;

// 예외를 전역적으로 처리할 수 있는 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommonException.class)
    protected ResponseEntity handleCommonException(CommonException e){
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(UserException.class)
    protected ResponseEntity handleHostException(UserException e){
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(FoodException.class)
    protected ResponseEntity handleFoodException(FoodException e){
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
    }
}