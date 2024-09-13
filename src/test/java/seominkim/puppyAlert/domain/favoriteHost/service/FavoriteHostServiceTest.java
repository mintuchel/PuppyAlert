package seominkim.puppyAlert.domain.favoriteHost.service;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// test end
@ExtendWith(MockitoExtension.class)
public class FavoriteHostServiceTest {
    @InjectMocks
    private FavoriteHostService favoriteHostService;

    @Mock
    private FavoriteHostRepository favoriteHostRepository;

    @Mock
    private UserRepository userRepository;

    private FavoriteHostRequest request;
    private User puppy, host;
    private FavoriteHost favoriteHost;

    @BeforeEach
    private void testSetUp(){
        request = favoriteHostRequest();

        host = User.builder()
                .id(request.hostId())
                .build();

        puppy = User.builder()
                .id(request.puppyId())
                .build();

        favoriteHost = FavoriteHost.builder()
                .favoriteHostId(11L)
                .puppy(puppy)
                .host(host)
                .build();
    }

    @Test
    @DisplayName("관심 호스트 추가 성공")
    public void addFavoriteHostSuccess(){
        // given
        given(favoriteHostRepository.existsByPuppyIdAndHostId(request.puppyId(), request.hostId())).willReturn(false);
        given(userRepository.findById(request.hostId())).willReturn(Optional.of(host));
        given(userRepository.findById(request.puppyId())).willReturn(Optional.of(puppy));
        given(favoriteHostRepository.save(any(FavoriteHost.class))).willReturn(favoriteHost);

        // when
        Long favoriteHostId = favoriteHostService.addFavoriteHost(request);

        // then
        verify(favoriteHostRepository).save(any(FavoriteHost.class)); // 호출된거 확인

        Assertions.assertThat(favoriteHostId).isNotNull();
    }

    @Test
    @DisplayName("관심 호스트 삭제 성공")
    public void deleteFavoriteHostSuccess() {
        // given
        given(favoriteHostRepository.existsByPuppyIdAndHostId(request.puppyId(), request.hostId())).willReturn(true);
        given(favoriteHostRepository.findByPuppyIdAndHostId(request.puppyId(),request.hostId())).willReturn(favoriteHost);

        // when
        Long favoriteHostId = favoriteHostService.deleteFavoriteHost(request);

        // then
        Assertions.assertThat(favoriteHostId).isNotNull();
        verify(favoriteHostRepository).delete(any(FavoriteHost.class));
    }

    private FavoriteHostRequest favoriteHostRequest(){
        return new FavoriteHostRequest(
                "ronaldo",
                "messi"
        );
    }
}
