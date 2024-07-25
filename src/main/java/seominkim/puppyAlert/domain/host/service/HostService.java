package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;
import seominkim.puppyAlert.global.dto.UserInfoResponseDTO;
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
    public String signUp(SignUpRequestDTO signUpDTO){

        Host host = new Host();
        host.setHostId(signUpDTO.getId());
        host.setPassword(signUpDTO.getPassword());
        host.setNickName(signUpDTO.getNickName());
        host.setName(signUpDTO.getName());
        host.setBirth(signUpDTO.getBirth());
        host.setPhoneNumber(signUpDTO.getPhoneNumber());
        host.setAddress(signUpDTO.getAddress());
        host.setLocation(signUpDTO.getLocation());

        hostRepository.save(host);
        return host.getHostId();
    }

    // Host 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponseDTO> findAll(){
        return hostRepository.findAll().stream()
                .map(host -> {
                    UserInfoResponseDTO dto = new UserInfoResponseDTO();
                    dto.setUserId(host.getHostId());
                    dto.setNickName(host.getNickName());
                    dto.setName(host.getName());
                    dto.setBirth(host.getBirth());
                    dto.setAddress(host.getAddress());
                    dto.setLocation(host.getLocation());
                    dto.setPhoneNumber(host.getPhoneNumber());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Host 단건 검색
    @Transactional(readOnly = true)
    public UserInfoResponseDTO findById(String hostId){
        Optional<Host> result = hostRepository.findById(hostId);
        return result.map(host -> {
            UserInfoResponseDTO dto = new UserInfoResponseDTO();
            dto.setUserId(host.getHostId());
            dto.setName(host.getName());
            dto.setBirth(host.getBirth());
            dto.setAddress(host.getAddress());
            dto.setLocation(host.getLocation());
            dto.setPhoneNumber(host.getPhoneNumber());
            return dto;
        }).orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));
    }
}
