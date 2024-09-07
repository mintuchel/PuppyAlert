package seominkim.puppyAlert.domain.user.dto.response;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DayFoodResponse(
        @NotBlank String partnerNickName,
        @NotBlank String menuName,
        @NotBlank String imageUrl,
        @NotBlank LocalDateTime time,
        @NotBlank String address,
        @NotBlank String detailAddress
){ }
