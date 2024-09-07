package seominkim.puppyAlert.domain.openAI.dto.response;

import jakarta.validation.constraints.NotBlank;

public record FoodRecipeResponse (
        @NotBlank String recipe
){ }
