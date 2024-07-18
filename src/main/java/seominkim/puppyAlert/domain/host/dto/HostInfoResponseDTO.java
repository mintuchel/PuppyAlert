package seominkim.puppyAlert.domain.host.dto;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@Getter
@Setter
public class HostInfoResponseDTO {
    private String hostId;

    private String name;

    private LocalDate birth;

    private String phoneNumber;

    @Embedded
    private Location location;
}
