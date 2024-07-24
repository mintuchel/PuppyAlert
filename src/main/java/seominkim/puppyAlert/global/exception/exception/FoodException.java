package seominkim.puppyAlert.global.exception.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;

@Getter
@AllArgsConstructor
public class FoodException extends RuntimeException{
    private ErrorCode errorCode;
}
