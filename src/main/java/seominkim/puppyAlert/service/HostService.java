package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.repository.HostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;

    // Host 회원가입
    @Transactional
    public String join(Host host){
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
