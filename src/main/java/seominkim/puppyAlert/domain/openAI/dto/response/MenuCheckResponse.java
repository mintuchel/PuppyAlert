package seominkim.puppyAlert.domain.openAI.dto.response;

import jakarta.validation.constraints.NotBlank;

public record MenuCheckResponse(
        @NotBlank boolean isRealFood
) { }
