package seominkim.puppyAlert.domain.food.dto;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import java.time.LocalDateTime;

public record FoodRequest(
        @NotBlank String hostId,
        @NotBlank String menu,
        @NotBlank LocalDateTime time,
        @NotBlank FoodStatus status
) {}
