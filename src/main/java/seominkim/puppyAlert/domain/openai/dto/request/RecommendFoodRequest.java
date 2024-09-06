package seominkim.puppyAlert.domain.openai.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RecommendFoodRequest(
        @NotBlank String categoryType,
        @NotBlank String ingredients
) { }
