package seominkim.puppyAlert.domain.food.dto;

import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.global.entity.Location;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record FoodResponse(
        @NotBlank Long foodId,
        @NotBlank String hostId,
        @NotBlank String menuName,
        @NotBlank String imageURL,
        @NotBlank LocalDateTime time,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank FoodStatus status
) {}
