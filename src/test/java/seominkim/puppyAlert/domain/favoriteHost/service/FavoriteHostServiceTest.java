package seominkim.puppyAlert.domain.favoriteHost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FavoriteHostServiceTest {
    @InjectMocks
    private FavoriteHostService favoriteHostService;

    @Mock private FavoriteHostRepository favoriteHostRepository;
    @Mock private HostRepository hostRepository;
    @Mock private PuppyRepository puppyRepository;

    private Host host;
    private Puppy puppy;
    private FavoriteHost favoriteHost;
    private FavoriteHostRequest request;
    String hostId = "ronaldo";
    String puppyId = "messi";

    @BeforeEach
    public void testSetUp(){
        host = new Host();
        host.setHostId(hostId);

        puppy = new Puppy();
        puppy.setPuppyId(puppyId);

        favoriteHost = new FavoriteHost();
        favoriteHost.setHost(host);
        favoriteHost.setPuppy(puppy);
        favoriteHost.setFavoriteHostId(3L);

        request = favoriteHostRequest();
    }

    @Test
    @DisplayName("관심 호스트 추가")
    public void addFavoriteHost(){
        // given
        given(favoriteHostRepository.existsByPuppy_PuppyIdAndHost_HostId(puppyId, hostId)).willReturn(false);
        given(hostRepository.findById(hostId)).willReturn(Optional.of(host));
        given(puppyRepository.findById(puppyId)).willReturn(Optional.of(puppy));
        given(favoriteHostRepository.save(any(FavoriteHost.class))).willReturn(favoriteHost);

        // when
        Long savedId = favoriteHostService.addFavoriteHost(request);

        // then
        verify(favoriteHostRepository, times(1)).existsByPuppy_PuppyIdAndHost_HostId(puppyId, hostId);
        verify(hostRepository, times(1)).findById(hostId);
        verify(puppyRepository, times(1)).findById(puppyId);
        verify(favoriteHostRepository, times(1)).save(any(FavoriteHost.class));

        assertNotNull(savedId);
    }

    @Test
    @DisplayName("관심 호스트 삭제")
    public void deleteFavoriteHostTest() {
        // given
        given(favoriteHostRepository.existsByPuppy_PuppyIdAndHost_HostId(puppyId, hostId)).willReturn(true);
        given(favoriteHostRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId)).willReturn(favoriteHost);

        // when
        Long savedId = favoriteHostService.deleteFavoriteHost(request);

        // then
        assertNotNull(savedId);
    }

    @Test
    @DisplayName("관심 호스트 조회")
    public void getFavoriteHostTest(){
        // given
        given(favoriteHostRepository.existsByPuppy_PuppyIdAndHost_HostId(puppyId, hostId)).willReturn(true);

        // when
        Boolean isFavoriteHost = favoriteHostService.isFavoriteHost(puppy, host);

        // then
        Assertions.assertThat(isFavoriteHost).isTrue();
    }

    private FavoriteHostRequest favoriteHostRequest(){
        return new FavoriteHostRequest(
                "ronaldo",
                "messi"
        );
    }
}
