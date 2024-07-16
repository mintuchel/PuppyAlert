package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.entity.Puppy;
import seominkim.puppyAlert.repository.PuppyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;

    // Host 회원가입
    @Transactional
    public String join(Puppy puppy){
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

    // Puppy 검색
    @Transactional(readOnly = true)
    public Puppy findById(String hostId){
        Optional<Puppy> puppy = puppyRepository.findById(hostId);
        if(puppy.isPresent()){
            return puppy.get();
        }else{
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }
}
