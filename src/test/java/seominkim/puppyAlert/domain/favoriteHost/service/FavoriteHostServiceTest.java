package seominkim.puppyAlert.domain.favoriteHost.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;

@ExtendWith(MockitoExtension.class)
public class FavoriteHostServiceTest {
    @InjectMocks
    private FavoriteHostService favoriteHostService;

    @Mock private FavoriteHostRepository favoriteHostRepository;
    @Mock private HostRepository hostRepository;
    @Mock private PuppyRepository puppyRepository;

    @Test
    @DisplayName("관심 호스트 추가")
    public void addFavoriteHost(){
        // given
        FavoriteHostRequest favoriteHostRequest = favoriteHostRequest();

        //given(favoriteHostRepository.)
        // when

        // then

    }

    @Test
    @DisplayName("관심 호스트 삭제")
    public void deleteFavoriteHostTest() {
        // given
    }

    @Test
    @DisplayName("관심 호스트 조회")
    public void getFavoriteHostTest(){
        // given
    }

    private FavoriteHostRequest favoriteHostRequest(){
        return new FavoriteHostRequest(
                "halland",
                "philfoden"
        );
    }
}
