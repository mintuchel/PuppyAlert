package seominkim.puppyAlert.domain.food.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodResponseDTO {

    @NotBlank
    private Long foodId;

    @NotBlank
    private String hostId;

    @NotBlank
    private String menu; // 메뉴이름

    @NotBlank
    private LocalDateTime time; // 식사시간

    @NotBlank
    private String imageURL;

    // address 랑 location 은 Food Entity 의 Host 참조해서 세팅해서 dto 로 보냄
    @NotBlank
    private String address;

    @NotBlank
    private Location location;

    @NotBlank
    private FoodStatus status; // 매칭상태
}
