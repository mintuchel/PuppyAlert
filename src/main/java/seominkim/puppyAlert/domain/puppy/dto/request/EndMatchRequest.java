package seominkim.puppyAlert.domain.puppy.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EndMatchRequest(
        @NotBlank String puppyId,
        @NotBlank Long foodId
) { }
