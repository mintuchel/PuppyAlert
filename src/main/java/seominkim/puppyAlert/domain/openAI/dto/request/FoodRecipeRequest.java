package seominkim.puppyAlert.domain.openAI.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FoodRecipeRequest(
    @NotBlank String foodName
) { }
