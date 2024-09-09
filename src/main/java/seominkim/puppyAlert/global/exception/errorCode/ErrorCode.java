package seominkim.puppyAlert.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 회원가입
    EXISTING_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다"),
    EXISTING_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다"),

    // 로그인
    INVALID_ID(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "비밀번호가 옳지 않습니다"),

    // 옳지 않은 USERTYPE
    INVALID_USERTYPE(HttpStatus.BAD_REQUEST, "옳지 않은 유저타입입니다"),

    // USER 존재성 확인
    NON_EXISTING_USER(HttpStatus.NOT_FOUND, "등록되지 않은 회원입니다"),

    // FOOD 존재성 확인
    NON_EXISTING_FOOD(HttpStatus.NOT_FOUND, "등록되지 않은 집밥입니다"),

    // 오늘의 집밥
    NO_TODAY_FOOD(HttpStatus.NOT_FOUND, "오늘 매칭된 집밥이 없습니다!"),
    // NO_MATCHED_TODAY_FOOD(HttpStatus.NOT_FOUND, "아직 매칭된 집밥이 없습니다!"),

    // FOOD STATUS 확인
    ALREADY_MATCHED(HttpStatus.BAD_REQUEST, "이미 매칭된 집밥입니다"),

    // FAVORITE HOST 등록 여부 확인
    ALREADY_FAVORITE_HOST(HttpStatus.BAD_REQUEST, "이미 등록된 관심 호스트입니다"),

    // FAVORITE HOST 삭제 여부 확인
    DELETED_FAVORITE_HOST(HttpStatus.BAD_REQUEST, "이미 삭제된 관심 호스트입니다");

    private final HttpStatus status;
    private final String message;
}