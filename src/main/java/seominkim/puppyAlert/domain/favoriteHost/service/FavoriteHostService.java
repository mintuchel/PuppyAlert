package seominkim.puppyAlert.domain.favoriteHost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.favoriteHost.repository.FavoriteHostRepository;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteHostService {

    private final FavoriteHostRepository favoriteHostRepository;
    private final HostRepository hostRepository;
    private final PuppyRepository puppyRepository;

    @Transactional
    public void addFavoriteHost(FavoriteHostRequestDTO favoriteHostRequestDTO){
        FavoriteHost favoriteHost = new FavoriteHost();
        Host host = hostRepository.findById(favoriteHostRequestDTO.getHostId()).get();
        Puppy puppy = puppyRepository.findById(favoriteHostRequestDTO.getPuppyId()).get();

        favoriteHost.setHost(host);
        favoriteHost.setPuppy(puppy);

        favoriteHostRepository.save(favoriteHost);
    }

    @Transactional
    public void deleteFavoriteHost(FavoriteHostRequestDTO favoriteHostRequestDTO){
        String puppyId = favoriteHostRequestDTO.getPuppyId();
        String hostId = favoriteHostRequestDTO.getHostId();

        FavoriteHost favoriteHost = favoriteHostRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId).get();
        favoriteHostRepository.delete(favoriteHost);
    }

    @Transactional(readOnly = true)
    public List<FavoriteHost> findAll(String puppyId){
        return favoriteHostRepository.findByPuppy_PuppyId(puppyId);
    }
}
