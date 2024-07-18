package seominkim.puppyAlert.domain.zipbob.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class ZipbobDTO {

        @NotBlank
        private String hostId;

        // Host 가 집밥 추가할때는 Blank
        // Puppy 랑 매칭됐을때는 Blank 아님
        private String puppyId;

        @NotBlank
        private String menu; // 메뉴이름

        @NotBlank
        private LocalDateTime time; // 식사시간

        @NotBlank
        private ZipbobStatus status; // 매칭상태
}