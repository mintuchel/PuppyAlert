package seominkim.puppyAlert.global.dto.response;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record MatchHistoryResponse(
        @NotBlank String partnerId,
        @NotBlank String menuName,
        @NotBlank LocalDateTime localDateTime
) {}