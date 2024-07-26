package seominkim.puppyAlert.global.dto;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.global.entity.Location;
import java.time.LocalDate;

public record UserInfoResponse(
        @NotBlank String userId,
        @NotBlank String name,
        @NotBlank String nickName,
        @NotBlank LocalDate birth,
        @NotBlank String phoneNumber,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location
) {}