package seominkim.puppyAlert.domain.openAI.dto.response;

import jakarta.validation.constraints.NotBlank;

public record RecommendFoodResponse(
        @NotBlank String menuName,
        @NotBlank String imageURL,
        @NotBlank String difficulty,
        @NotBlank String description
) { }