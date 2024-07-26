package seominkim.puppyAlert.domain.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;

public record SignUpRequest(
        @NotBlank String id,
        @NotBlank String password,
        @NotBlank String nickName,
        @NotBlank String name,
        @NotBlank LocalDate birth,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank String phoneNumber,
        @NotBlank UserType userType
) {}