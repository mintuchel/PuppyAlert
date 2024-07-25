package seominkim.puppyAlert.domain.common.dto.response;

import seominkim.puppyAlert.global.entity.UserType;

public record LoginResponse(String id, UserType userType) {}