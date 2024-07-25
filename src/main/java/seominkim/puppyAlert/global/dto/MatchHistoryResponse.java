package seominkim.puppyAlert.global.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record MatchHistoryResponse(
        @NotBlank String partnerId,
        @NotBlank String menu,
        @NotBlank LocalDateTime localDateTime
) {}