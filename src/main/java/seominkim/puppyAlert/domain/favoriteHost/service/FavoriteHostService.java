package seominkim.puppyAlert.domain.favoriteHost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostResponseDTO;
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
    public Long addFavoriteHost(FavoriteHostRequestDTO favoriteHostRequestDTO){
        Host host = hostRepository.findById(favoriteHostRequestDTO.getHostId()).get();
        Puppy puppy = puppyRepository.findById(favoriteHostRequestDTO.getPuppyId()).get();

        if(favoriteHostRepository.existsByHostAndPuppy(host, puppy)){
            throw new IllegalArgumentException("FavoriteHost already exists for the given host and puppy");
        }

        FavoriteHost favoriteHost = new FavoriteHost();
        favoriteHost.setHost(host);
        favoriteHost.setPuppy(puppy);

        return favoriteHostRepository.save(favoriteHost).getFavoriteHostId();
    }

    @Transactional
    public Long deleteFavoriteHost(FavoriteHostRequestDTO favoriteHostRequestDTO){
        String puppyId = favoriteHostRequestDTO.getPuppyId();
        String hostId = favoriteHostRequestDTO.getHostId();

        FavoriteHost favoriteHost = favoriteHostRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId).get();
        favoriteHostRepository.delete(favoriteHost);

        return favoriteHost.getFavoriteHostId();
    }

    // foodService 통해서 가장 최근시간 JPQL 로 확인
    @Transactional(readOnly = true)
    public List<FavoriteHostResponseDTO> findAll(String puppyId){
        return favoriteHostRepository.findByPuppy_PuppyId(puppyId).stream()
                .map(favoriteHost -> {
                    FavoriteHostResponseDTO dto = new FavoriteHostResponseDTO();
                    dto.setHostId(favoriteHost.getHost().getHostId());
                    dto.setRecentZipbobTime(foodService.getMostRecentFood(puppyId, dto.getHostId()).getTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
