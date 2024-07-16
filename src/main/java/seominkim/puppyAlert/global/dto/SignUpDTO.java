package seominkim.puppyAlert.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@Getter
@Setter
public class SignUpDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birth;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private Location location;
}
