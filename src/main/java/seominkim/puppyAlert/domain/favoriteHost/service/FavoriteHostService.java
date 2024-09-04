package seominkim.puppyAlert.domain.favoriteHost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

@Service
@RequiredArgsConstructor
public class FavoriteHostService {

    private final FavoriteHostRepository favoriteHostRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean isFavoriteHost(String puppyId, String hostId) {
        return favoriteHostRepository.existsByPuppyIdAndHostId(puppyId, hostId);
    }

    // 관심 HOST 추가
    @Transactional
    public Long addFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        String puppyId = favoriteHostRequest.puppyId();
        String hostId = favoriteHostRequest.hostId();

        if(favoriteHostRepository.existsByPuppyIdAndHostId(puppyId, hostId)){
            throw new PuppyException(ErrorCode.ALREADY_FAVORITE_HOST);
        }

        FavoriteHost favoriteHost = new FavoriteHost();
        favoriteHost.setHost(userRepository.findById(hostId).get());
        favoriteHost.setPuppy(userRepository.findById(puppyId).get());

        return favoriteHostRepository.save(favoriteHost).getFavoriteHostId();
    }

    // 관심 HOST 삭제
    @Transactional
    public Long deleteFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        String puppyId = favoriteHostRequest.puppyId();
        String hostId = favoriteHostRequest.hostId();

        if(!favoriteHostRepository.existsByPuppyIdAndHostId(puppyId, hostId)){
            throw new PuppyException(ErrorCode.DELETED_FAVORITE_HOST);
        }

        FavoriteHost favoriteHost = favoriteHostRepository.findByPuppyIdAndHostId(puppyId, hostId);
        favoriteHostRepository.delete(favoriteHost);

        return favoriteHost.getFavoriteHostId();
    }
}
