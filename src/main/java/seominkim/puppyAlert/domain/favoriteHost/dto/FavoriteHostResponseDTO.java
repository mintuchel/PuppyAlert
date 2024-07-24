package seominkim.puppyAlert.domain.favoriteHost.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FavoriteHostResponseDTO {
    @NotBlank
    private String hostId;

    private LocalDateTime recentFoodTime;
}
