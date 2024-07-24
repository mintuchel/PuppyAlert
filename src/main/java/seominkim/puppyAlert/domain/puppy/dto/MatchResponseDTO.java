package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponseDTO {
    @NotBlank
    private Long foodId;

    @NotBlank
    private String hostId;

    @NotBlank
    private String puppyId;
}
