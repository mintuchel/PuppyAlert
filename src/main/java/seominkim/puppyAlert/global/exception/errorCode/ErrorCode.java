package seominkim.puppyAlert.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 회원가입
    EXISTING_ID_ERROR(HttpStatus.BAD_REQUEST, "이미 존재하는 ID입니다"),

    // 로그인
    INVALID_LOGIN_ERROR(HttpStatus.BAD_REQUEST, "등록되지 않은 회원입니다");

    private final HttpStatus status;
    private final String message;
}