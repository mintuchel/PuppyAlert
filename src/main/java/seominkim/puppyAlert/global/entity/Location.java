package seominkim.puppyAlert.global.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Location {
    private Long latitude;
    private Long longitude;

    protected Location(){

    }

    public Location(Long latitude, Long longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
