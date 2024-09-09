package seominkim.puppyAlert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CancelFoodRequest(
        @NotBlank Long foodId,
        @NotBlank String userId
) { }
