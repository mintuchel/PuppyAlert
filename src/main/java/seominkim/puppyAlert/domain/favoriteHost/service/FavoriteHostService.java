package seominkim.puppyAlert.domain.favoriteHost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

@Service
@RequiredArgsConstructor
public class FavoriteHostService {

    private final FavoriteHostRepository favoriteHostRepository;
    private final HostRepository hostRepository;
    private final PuppyRepository puppyRepository;

    @Transactional(readOnly = true)
    public boolean isFavoriteHost(Puppy puppy, Host host) {
        return favoriteHostRepository.existsByPuppyAndHost(puppy, host);
    }

    // 관심 HOST 추가
    @Transactional
    public Long addFavoriteHost(FavoriteHostRequest favoriteHostRequest){

        Host host = hostRepository.findById(favoriteHostRequest.hostId()).get();
        Puppy puppy = puppyRepository.findById(favoriteHostRequest.puppyId()).get();

        if(favoriteHostRepository.existsByPuppyAndHost(puppy, host)){
            throw new PuppyException(ErrorCode.ALREADY_FAVORITE_HOST);
        }

        FavoriteHost favoriteHost = new FavoriteHost();
        favoriteHost.setHost(host);
        favoriteHost.setPuppy(puppy);

        return favoriteHostRepository.save(favoriteHost).getFavoriteHostId();
    }

    // 관심 HOST 삭제
    @Transactional
    public Long deleteFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        Host host = hostRepository.findById(favoriteHostRequest.hostId()).get();
        Puppy puppy = puppyRepository.findById(favoriteHostRequest.puppyId()).get();

        FavoriteHost favoriteHost = favoriteHostRepository.findByPuppyAndHost(puppy, host);
        favoriteHostRepository.delete(favoriteHost);

        return favoriteHost.getFavoriteHostId();
    }
}
