package seominkim.puppyAlert.domain.zipbob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.repository.ZipbobRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ZipbobService {
    private final ZipbobRepository zipbobRepository;

    @Transactional
    public Long add(Zipbob zipbob){
        zipbobRepository.save(zipbob);
        return zipbob.getZipbobId();
    }

    public List<Zipbob> findAll(){
        return zipbobRepository.findAll();
    }

    public Zipbob findOne(Long zipbobId){
        Optional<Zipbob> zipbob = zipbobRepository.findById(zipbobId);
        if(zipbob.isPresent()){
            return zipbob.get();
        }else{
            throw new IllegalStateException("존재하지 않는 집밥입니다.");
        }
    }
}
