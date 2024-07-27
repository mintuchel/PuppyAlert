package seominkim.puppyAlert.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 회원가입
    EXISTING_ID(HttpStatus.UNPROCESSABLE_ENTITY, "이미 존재하는 ID입니다"),
    EXISTING_NICKNAME(HttpStatus.UNPROCESSABLE_ENTITY, "이미 존재하는 닉네임입니다"),

    // 로그인
    INVALID_ID(HttpStatus.NOT_FOUND, "존재하지 않는 ID입니다"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "존재하지 않는 PASSWORD입니다"),

    // 옳지 않은 USERTYPE
    INVALID_USERTYPE(HttpStatus.BAD_REQUEST, "옳지 않은 유저타입입니다"),

    // USER 존재성 확인
    NON_EXISTING_USER(HttpStatus.NOT_FOUND, "등록되지 않은 회원입니다"),

    // FOOD 존재성 확인
    NON_EXISTING_FOOD(HttpStatus.NOT_FOUND, "등록되지 않은 집밥입니다"),

    // FOOD 상태 확인
    ALREADY_MATCHED(HttpStatus.BAD_REQUEST, "이미 매칭된 집밥입니다"),

    // 최근 집밥 약속 확인
    NO_RECENT_MATCH(HttpStatus.NOT_FOUND, "최근 집밥을 먹은 적이 없습니다");

    private final HttpStatus status;
    private final String message;
}