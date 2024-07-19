package seominkim.puppyAlert.domain.zipbob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobRequestDTO;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.repository.ZipbobRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZipbobService {
    private final ZipbobRepository zipbobRepository;
    private final HostRepository hostRepository;

    @Transactional
    public Long add(ZipbobRequestDTO zipbobRequestDTO){
        Host providerHost = hostRepository.findById(zipbobRequestDTO.getHostId()).get();

        Zipbob zipbob = new Zipbob();

        // 아직 정해지지 않은 필드가 있는 경우에도 그냥 save 바로 해서 em.persist 해도 되나?
        zipbob.setHost(providerHost);
        zipbob.setTime(zipbobRequestDTO.getTime());
        zipbob.setStatus(zipbobRequestDTO.getStatus());
        zipbob.setMenu(zipbobRequestDTO.getMenu());

        zipbobRepository.save(zipbob);
        return zipbob.getZipbobId();
    }

    @Transactional(readOnly = true)
    public List<Zipbob> findAll(){
        return zipbobRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Zipbob findOne(Long zipbobId){
        Optional<Zipbob> zipbob = zipbobRepository.findById(zipbobId);
        if(zipbob.isPresent()){
            return zipbob.get();
        }else{
            throw new IllegalStateException("존재하지 않는 집밥입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<Zipbob> findPuppyHistory(String puppyId){
        return zipbobRepository.findByPuppy_PuppyId(puppyId);
    }

    @Transactional(readOnly = true)
    public List<Zipbob> findHostHistory(String hostId){
        return zipbobRepository.findByHost_HostId(hostId);
    }

    @Transactional(readOnly = true)
    public List<Zipbob> findFavoriteHostHistory(String puppyId, String hostId){
        return zipbobRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId);
    }
}