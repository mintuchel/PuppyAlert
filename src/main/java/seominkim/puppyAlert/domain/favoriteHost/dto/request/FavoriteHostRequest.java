package seominkim.puppyAlert.domain.favoriteHost.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FavoriteHostRequest(
        @NotBlank String hostId,
        @NotBlank String puppyId
) {}
