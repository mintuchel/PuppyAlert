package seominkim.puppyAlert.domain.shop.dto.response;

import jakarta.validation.constraints.NotBlank;

public record ShopResponse(
    @NotBlank String shopName,
    @NotBlank String detailAddress,
    @NotBlank String productType
) { }
