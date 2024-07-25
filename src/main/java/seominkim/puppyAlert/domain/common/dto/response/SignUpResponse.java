package seominkim.puppyAlert.domain.common.dto.response;

import seominkim.puppyAlert.global.entity.UserType;

public record SignUpResponse(String id, UserType userType) {}