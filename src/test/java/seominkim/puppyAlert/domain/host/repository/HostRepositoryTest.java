package seominkim.puppyAlert.domain.host.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Host host;

    @BeforeEach
    private void testSetUp(){
        host = new Host();
        host.setHostId("Ronaldo");
    }

    @DisplayName("호스트 추가")
    @Test
    void addHost(){
        // given

        // when
        Host savedHost = hostRepository.save(host);

        // then
        Assertions.assertThat(savedHost.getHostId()).isEqualTo(host.getHostId());
    }

    @DisplayName("호스트 조회")
    @Test
    void findHost(){
        // given

        // when
        hostRepository.save(host);
        Host findHost = hostRepository.findById(host.getHostId())
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        // then
        Assertions.assertThat(host.getHostId()).isEqualTo(findHost.getHostId());
    }
}
