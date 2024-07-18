package seominkim.puppyAlert.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
