package seominkim.puppyAlert.domain.user.dto.response;

import seominkim.puppyAlert.global.entity.UserType;

public record LoginResponse(String nickName, UserType userType) {}