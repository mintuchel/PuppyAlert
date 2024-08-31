package seominkim.puppyAlert.domain.user.dto.response;

import seominkim.puppyAlert.global.entity.UserType;

public record SignUpResponse(String id, UserType userType) {}