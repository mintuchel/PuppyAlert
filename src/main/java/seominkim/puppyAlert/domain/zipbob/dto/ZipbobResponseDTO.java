package seominkim.puppyAlert.domain.zipbob.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;

@Getter
@Setter
public class ZipbobResponseDTO {
    @NotBlank
    private String hostId;

    @NotBlank
    private String menu; // 메뉴이름

    @NotBlank
    private LocalDateTime time; // 식사시간

    @NotBlank
    private String imageURL;

    @NotBlank
    private Location location;

    @NotBlank
    private ZipbobStatus status; // 매칭상태
}
