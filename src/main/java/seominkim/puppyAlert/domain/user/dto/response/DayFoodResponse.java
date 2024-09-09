package seominkim.puppyAlert.domain.user.dto.response;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.domain.food.entity.DiningStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;

public record DayFoodResponse(
        @NotBlank Long foodId,
        @NotBlank String partnerId,
        @NotBlank String partnerNickName,
        @NotBlank String menuName,
        @NotBlank String imageUrl,
        @NotBlank LocalDateTime time,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank DiningStatus status
){ }
