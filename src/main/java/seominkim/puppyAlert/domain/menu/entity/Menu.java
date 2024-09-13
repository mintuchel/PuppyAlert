package seominkim.puppyAlert.domain.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

// Menu는 DB에 들어가면 불변성 객체임
// 멤버변수가 수정될 일이 없음

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu {
    @Id
    private String menuName;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String imageURL;
}
