package seominkim.puppyAlert.domain.zipbob.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class ZipbobRequestDTO {

        @NotBlank
        private String hostId;

        @NotBlank
        private String menu; // 메뉴이름

        @NotBlank
        private LocalDateTime time; // 식사시간

        @NotBlank
        private ZipbobStatus status; // 매칭상태
}