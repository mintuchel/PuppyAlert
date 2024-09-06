package seominkim.puppyAlert.domain.openai.dto.response;

import jakarta.validation.constraints.NotBlank;

public record RecommendFoodResponse(
    @NotBlank String menu1,
    @NotBlank String menu2,
    @NotBlank String menu3
) { }
