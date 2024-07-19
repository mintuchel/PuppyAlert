package seominkim.puppyAlert.domain.favoriteHost.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteHostRequestDTO {
    @NotBlank
    private String hostId;

    @NotBlank
    private String puppyId;
}
