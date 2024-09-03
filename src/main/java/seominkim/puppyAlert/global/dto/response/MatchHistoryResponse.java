package seominkim.puppyAlert.global.dto.response;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;

public record MatchHistoryResponse(
        @NotBlank long foodId,
        @NotBlank String partnerId,
        @NotBlank String partnerNickName,
        @NotBlank String menuName,
        @NotBlank String imageURL,
        @NotBlank String address,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank LocalDateTime localDateTime,
        String partnerProfileImageUrl
) {}