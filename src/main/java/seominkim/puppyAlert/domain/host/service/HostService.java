package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.global.dto.MatchHistoryResponse;
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
                            host.getDetailAddress(),
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
                    host.getDetailAddress(),
                    host.getLocation()
            );
            return dto;
        }).orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));
    }

    // Host 집밥 기록 검색
    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> getHistory(String hostId){
        Host host = hostRepository.findById(hostId).get();
        return host.getFoodList().stream()
                .map(food-> new MatchHistoryResponse(
                        food.getPuppy().getPuppyId(),
                        food.getMenu(),
                        food.getTime()
                ))
                .collect(Collectors.toList());
    }
}
