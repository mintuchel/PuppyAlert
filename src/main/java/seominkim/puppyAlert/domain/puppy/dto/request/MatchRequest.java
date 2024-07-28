package seominkim.puppyAlert.domain.puppy.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MatchRequest(
        @NotBlank Long foodId,
        @NotBlank String puppyId
) {}