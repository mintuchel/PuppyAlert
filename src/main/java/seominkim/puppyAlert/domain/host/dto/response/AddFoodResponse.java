package seominkim.puppyAlert.domain.host.dto.response;

import jakarta.validation.constraints.NotBlank;

public record AddFoodResponse(@NotBlank Long foodId, @NotBlank String imageURL){}
