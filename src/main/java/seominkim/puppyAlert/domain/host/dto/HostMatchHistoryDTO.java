package seominkim.puppyAlert.domain.host.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HostMatchHistoryDTO {
    @NotBlank
    private String puppyId;

    @NotBlank
    private String menu;

    @NotBlank
    private LocalDateTime localDateTime;
}