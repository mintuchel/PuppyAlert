package seominkim.puppyAlert.domain.market.dto.response;

import jakarta.validation.constraints.NotBlank;
import seominkim.puppyAlert.global.entity.Location;

public record MarketResponse(
        @NotBlank int id,
        @NotBlank String name,
        @NotBlank String detailAddress,
        @NotBlank Location location,
        @NotBlank String imageURL
){}
