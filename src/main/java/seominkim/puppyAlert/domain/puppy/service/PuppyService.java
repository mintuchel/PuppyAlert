package seominkim.puppyAlert.domain.puppy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.domain.zipbob.repository.ZipbobRepository;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;
    private final ZipbobRepository zipbobRepository;

    // Puppy 회원가입
    @Transactional
    public String signUp(SignUpRequestDTO signUpDTO){
        Puppy puppy = new Puppy();
        puppy.setPuppyId(signUpDTO.getId());
        puppy.setPassword(signUpDTO.getPassword());
        puppy.setName(signUpDTO.getName());
        puppy.setPhoneNumber(signUpDTO.getPhoneNumber());
        puppy.setBirth(signUpDTO.getBirth());
        puppy.setLocation(signUpDTO.getLocation());

        validateDuplicatePuppy(puppy);
        puppyRepository.save(puppy);
        return puppy.getPuppyId();
    }

    private void validateDuplicatePuppy(Puppy puppy) {
        List<Puppy> findHosts = puppyRepository.findByName(puppy.getName());
        if (!findHosts.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 결식아동입니다.");
        }
    }

    @Transactional(readOnly = true)
    public boolean checkLogin(LoginRequestDTO loginRequestDTO){
        Optional<Puppy> loginPuppy = puppyRepository.findByPuppyIdAndPassword(loginRequestDTO.getId(), loginRequestDTO.getPassword());
        return loginPuppy.isPresent();
    }

    // Puppy 전체 검색
    @Transactional(readOnly = true)
    public List<Puppy> findAll(){
        return puppyRepository.findAll();
    }

    // Puppy 검색
    @Transactional(readOnly = true)
    public Puppy findById(String puppyId){
        Optional<Puppy> puppy = puppyRepository.findById(puppyId);
        if(puppy.isPresent()){
            return puppy.get();
        }else{
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    @Transactional
    public Zipbob matchZipbob(MatchRequestDTO matchRequestDTO){
        Zipbob zipbob = zipbobRepository.findById(matchRequestDTO.getZipbobId()).get();
        Puppy puppy = puppyRepository.findById(matchRequestDTO.getPuppyId()).get();
        
        // 집밥 업데이트
        zipbob.setPuppy(puppy);
        zipbob.setStatus(ZipbobStatus.MATCHED);

        // 업데이트된 집밥 저장
        zipbobRepository.save(zipbob);

        return zipbob;
    }

    @Transactional
    public void addFavoriteHost(FavoriteHostRequestDTO favoriteHostRequestDTO){

    }
}