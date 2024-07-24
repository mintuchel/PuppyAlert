package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchRequestDTO {
    @NotBlank
    private Long foodId;

    @NotBlank
    private String puppyId;
}
