package seominkim.puppyAlert.domain.food.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class FoodRequestDTO {

        @NotBlank
        private String hostId;

        @NotBlank
        private String menu; // 메뉴이름

        @NotBlank
        private LocalDateTime time; // 식사시간

        @NotBlank
        private FoodStatus status; // 매칭상태
}