package seominkim.puppyAlert.domain.user.dto.response;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;

public record DayFoodResponse(
        @NotBlank Long foodId,
        @NotBlank String partnerId,
        @NotBlank String partnerNickName,
        @NotBlank String menuName,
        @NotBlank String imageURL,
        @NotBlank LocalDateTime time,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank MatchStatus status
){ }
