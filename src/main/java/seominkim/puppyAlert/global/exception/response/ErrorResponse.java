package seominkim.puppyAlert.global.exception.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;

@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;

    public ErrorResponse(ErrorCode errorcode){
        this.status = errorcode.getStatus();
        this.message = errorcode.getMessage();
    }
}