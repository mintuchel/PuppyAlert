package seominkim.puppyAlert.domain.food.dto.response;

import jakarta.validation.constraints.NotBlank;

public record AddFoodResponse(@NotBlank Long foodId, @NotBlank String imageURL){}
