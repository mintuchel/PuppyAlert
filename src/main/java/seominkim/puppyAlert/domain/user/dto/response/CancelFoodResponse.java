package seominkim.puppyAlert.domain.user.dto.response;

import jakarta.validation.constraints.NotBlank;

public record CancelFoodResponse (
   @NotBlank Long foodId
){ }
