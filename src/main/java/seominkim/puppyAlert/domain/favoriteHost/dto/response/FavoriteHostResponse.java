package seominkim.puppyAlert.domain.favoriteHost.dto.response;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record FavoriteHostResponse(
        @NotBlank String hostId,
        LocalDateTime recentFoodTime
) {}

