package seominkim.puppyAlert.domain.host.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.HostException;

import java.time.LocalDate;

// DataJpaTest 는 기본적으로 각 테스트 이후에 모두 롤백해준다
@DataJpaTest
@ActiveProfiles("test")
public class HostRepositoryTest {
    @Autowired
    private HostRepository hostRepository;

    @DisplayName("호스트 추가")
    @Test
    void addHost(){
        // given
        Host host = host();

        // when
        Host savedHost = hostRepository.save(host);

        // then
        // Assertions.assertThat(savedHost).isEqualTo(host);
        Assertions.assertThat(savedHost.getHostId()).isEqualTo(host.getHostId());
        Assertions.assertThat(savedHost.getName()).isEqualTo(host.getName());
    }

    @DisplayName("호스트 조회")
    @Test
    void findHost(){
        // given
        Host host = host();

        // when
        hostRepository.save(host);
        Host findHost = hostRepository.findById(host.getHostId())
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        // then
        Assertions.assertThat(host.getHostId()).isEqualTo(findHost.getHostId());
    }

    private Host host(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setNickName("iam노쇼에요");
        host.setPassword("777");
        host.setBirth(LocalDate.now());
        host.setAddress("레알 마드리드");
        host.setDetailAddress("산티아고 베르나베우");
        host.setLocation(new Location(100.5, 100.1));
        host.setPhoneNumber("010-4822-3636");
        return host;
    }
}
