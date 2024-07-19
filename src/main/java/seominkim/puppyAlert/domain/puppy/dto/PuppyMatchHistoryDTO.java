package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PuppyMatchHistoryDTO {
    @NotBlank
    private String hostId;

    @NotBlank
    private String menu;

    @NotBlank
    private LocalDateTime localDateTime;
}
