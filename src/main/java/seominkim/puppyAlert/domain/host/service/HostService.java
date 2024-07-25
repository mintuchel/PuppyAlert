package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.common.dto.request.SignUpRequest;
import seominkim.puppyAlert.global.dto.UserInfoResponse;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.HostException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;

    // Host 회원가입
    @Transactional
    public String signUp(SignUpRequest signUpDTO){

        Host host = new Host();
        host.setHostId(signUpDTO.id());
        host.setPassword(signUpDTO.password());
        host.setNickName(signUpDTO.nickName());
        host.setName(signUpDTO.name());
        host.setBirth(signUpDTO.birth());
        host.setPhoneNumber(signUpDTO.phoneNumber());
        host.setAddress(signUpDTO.address());
        host.setLocation(signUpDTO.location());

        hostRepository.save(host);
        return host.getHostId();
    }

    // Host 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll(){
        return hostRepository.findAll().stream()
                .map(host -> {
                    UserInfoResponse dto = new UserInfoResponse(
                            host.getHostId(),
                            host.getName(),
                            host.getNickName(),
                            host.getBirth(),
                            host.getPhoneNumber(),
                            host.getAddress(),
                            host.getLocation()
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Host 단건 검색
    @Transactional(readOnly = true)
    public UserInfoResponse findById(String hostId){
        Optional<Host> result = hostRepository.findById(hostId);
        return result.map(host -> {
            UserInfoResponse dto = new UserInfoResponse(
                    host.getHostId(),
                    host.getName(),
                    host.getNickName(),
                    host.getBirth(),
                    host.getPhoneNumber(),
                    host.getAddress(),
                    host.getLocation()
            );
            return dto;
        }).orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));
    }
}
