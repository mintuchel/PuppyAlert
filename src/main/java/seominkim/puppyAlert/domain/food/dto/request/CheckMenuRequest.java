package seominkim.puppyAlert.domain.food.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CheckMenuRequest (
   @NotBlank String menuName
){ }
