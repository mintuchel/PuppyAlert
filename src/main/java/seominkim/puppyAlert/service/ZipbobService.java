package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.entity.Puppy;
import seominkim.puppyAlert.entity.Zipbob;
import seominkim.puppyAlert.repository.ZipbobRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZipbobService {
    private final ZipbobRepository zipbobRepository;

    // Zipbob 생성
    @Transactional
    public Long join(Zipbob zipbob){
        zipbobRepository.save(zipbob);
        return zipbob.getZipbobId();
    }

    // Zipbob 검색
    @Transactional(readOnly = true)
    public Zipbob findById(Long zipbobId){
        Optional<Zipbob> zipbob = zipbobRepository.findById(zipbobId);
        if(zipbob.isPresent()){
            return zipbob.get();
        }else{
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }
}
