package seominkim.puppyAlert.domain.favoriteHost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostResponse;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteHostService {

    private final FoodService foodService;

    private final HostRepository hostRepository;
    private final PuppyRepository puppyRepository;
    private final FavoriteHostRepository favoriteHostRepository;

    @Transactional
    public Long addFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        Host host = hostRepository.findById(favoriteHostRequest.hostId()).get();
        Puppy puppy = puppyRepository.findById(favoriteHostRequest.puppyId()).get();

        if(favoriteHostRepository.existsByHostAndPuppy(host, puppy)){
            throw new IllegalArgumentException("FavoriteHost already exists for the given host and puppy");
        }

        FavoriteHost favoriteHost = new FavoriteHost();
        favoriteHost.setHost(host);
        favoriteHost.setPuppy(puppy);

        return favoriteHostRepository.save(favoriteHost).getFavoriteHostId();
    }

    @Transactional
    public Long deleteFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        String puppyId = favoriteHostRequest.puppyId();
        String hostId = favoriteHostRequest.hostId();

        FavoriteHost favoriteHost = favoriteHostRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId).get();
        favoriteHostRepository.delete(favoriteHost);

        return favoriteHost.getFavoriteHostId();
    }

    // foodService 통해서 가장 최근시간 JPQL 로 확인
    @Transactional(readOnly = true)
    public List<FavoriteHostResponse> findAll(String puppyId){
        return favoriteHostRepository.findByPuppy_PuppyId(puppyId).stream()
                .map(favoriteHost -> {
                    String hostId = favoriteHost.getHost().getHostId();
                    FavoriteHostResponse response = new FavoriteHostResponse(
                            hostId,
                            foodService.getMostRecentFood(puppyId, hostId).getTime()
                    );
                    return response;
                })
                .collect(Collectors.toList());
    }
}
