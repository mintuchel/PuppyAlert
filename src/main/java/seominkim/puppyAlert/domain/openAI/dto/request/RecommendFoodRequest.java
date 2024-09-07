package seominkim.puppyAlert.domain.openAI.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RecommendFoodRequest(
        @NotBlank String categoryType,
        @NotBlank String ingredients
) { }
