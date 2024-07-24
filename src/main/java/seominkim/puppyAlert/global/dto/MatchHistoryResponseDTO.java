package seominkim.puppyAlert.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchHistoryResponseDTO {
    @NotBlank
    private String partnerId;

    @NotBlank
    private String menu;

    @NotBlank
    private LocalDateTime localDateTime;
}