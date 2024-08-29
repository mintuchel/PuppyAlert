package seominkim.puppyAlert.global.dto.response;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record MatchHistoryResponse(
        @NotBlank long foodId,
        @NotBlank String partnerId,
        @NotBlank String partnerNickName,
        @NotBlank String menuName,
        @NotBlank String imageURL,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank LocalDateTime localDateTime
) {}