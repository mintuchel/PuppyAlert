package seominkim.puppyAlert.domain.openAI.dto.response;

import jakarta.validation.constraints.NotBlank;

public record RecipeResponse(
        @NotBlank String step1,
        @NotBlank String step2,
        @NotBlank String step3,
        @NotBlank String step4,
        @NotBlank String step5
){ }
