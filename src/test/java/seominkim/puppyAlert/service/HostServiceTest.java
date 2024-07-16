package seominkim.puppyAlert.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@SpringBootTest
public class HostServiceTest {

    @Autowired HostService hostService;

    @Test
    public void joinTest(){
        Host host1 = Host.builder()
                .hostId("Havertz")
                .name("하베르츠")
                .password("123")
                .birth(LocalDate.now())
                .location(new Location(100L, 100L))
                .phoneNumber("010-4822-3636")
                .build();

        String savedId = hostService.join(host1);

        Host findHost = hostService.findById(savedId);

        Assertions.assertThat(findHost.getHostId()).isEqualTo(host1.getHostId());
    }
}