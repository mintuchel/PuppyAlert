package seominkim.puppyAlert.domain.puppy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.dto.LoginDTO;
import seominkim.puppyAlert.global.dto.SignUpDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;

    // Puppy 회원가입
    @Transactional
    public String signUp(SignUpDTO signUpDTO){
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
    public boolean checkLogin(LoginDTO loginDTO){
        Optional<Puppy> loginPuppy = puppyRepository.findByPuppyIdAndPassword(loginDTO.getId(), loginDTO.getPassword());
        return loginPuppy.isPresent();
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
}
