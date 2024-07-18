package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.global.dto.LoginDTO;
import seominkim.puppyAlert.global.dto.SignUpDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;

    // Host 회원가입
    @Transactional
    public String signUp(SignUpDTO signUpDTO){
        Host host = new Host();
        host.setHostId(signUpDTO.getId());
        host.setPassword(signUpDTO.getPassword());
        host.setName(signUpDTO.getName());
        host.setBirth(signUpDTO.getBirth());
        host.setPhoneNumber(signUpDTO.getPhoneNumber());
        host.setLocation(signUpDTO.getLocation());

        validateDuplicateHost(host);
        hostRepository.save(host);
        return host.getHostId();
    }

    private void validateDuplicateHost(Host host) {
        List<Host> findHosts = hostRepository.findByName(host.getName());
        if (!findHosts.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // Host 로그인 확인
    @Transactional(readOnly = true)
    public boolean checkLogin(LoginDTO loginDTO){
        Optional<Host> loginHost = hostRepository.findByHostIdAndPassword(loginDTO.getId(), loginDTO.getPassword());
        return loginHost.isPresent();
    }

    // Host 검색
    @Transactional(readOnly = true)
    public Host findById(String hostId){
        Optional<Host> host = hostRepository.findById(hostId);
        if(host.isPresent()){
            return host.get();
        }else{
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }
}
