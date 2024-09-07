package seominkim.puppyAlert.domain.host.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record AddFoodRequest(
        @NotBlank String hostId,
        @NotBlank String menuName,
        @NotBlank LocalDateTime time
) {}