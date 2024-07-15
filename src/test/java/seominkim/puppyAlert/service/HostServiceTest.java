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
        Host host = new Host();
        host.setHostId("seominkim");
        host.setName("서민김");
        host.setPassword("123");
        host.setBirth(LocalDate.now());
        host.setLocation(new Location(100L,100L));
        host.setPhoneNumber("010-4822-3636");
        String savedId = hostService.join(host);

        Host findHost = hostService.findById(savedId);

        Assertions.assertThat(findHost.getHostId()).isEqualTo(host.getHostId());
    }
}