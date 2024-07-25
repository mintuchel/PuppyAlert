package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;

public record MatchResponse(
        @NotBlank Long foodId,
        @NotBlank String hostId,
        @NotBlank String puppyId
) {}

