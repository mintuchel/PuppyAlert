package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;

public record MatchRequest(
        @NotBlank Long foodId,
        @NotBlank String puppyId
) {}