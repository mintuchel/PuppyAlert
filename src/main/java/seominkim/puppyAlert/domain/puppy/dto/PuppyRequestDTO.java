package seominkim.puppyAlert.domain.puppy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@Getter
@Setter
public class PuppyRequestDTO {
    @NotBlank
    private String puppyId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birth;

    @NotBlank
    private String phoneNumber;

    // Location 같은 커스텀 클래스 변수도 알아서 해당 클래스 참조해서 매핑이 됨!
    // 즉, longitude latitude 자동으로 매핑됨!
    @NotBlank
    private Location location;
}