package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.global.dto.response.MatchHistoryResponse;
import seominkim.puppyAlert.global.dto.response.UserInfoResponse;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.HostException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostService {
    private final FoodService foodService;

    private final HostRepository hostRepository;

    // 집밥 추가
    @Transactional
    public AddFoodResponse addFood(FoodRequest foodRequest){
        Host providerHost = hostRepository.findById(foodRequest.hostId())
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        return foodService.addNewFood(providerHost, foodRequest);
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
        return hostRepository.findById(hostId).map(host -> {
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
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        return host.getFoodList().stream()
                .map(food -> new MatchHistoryResponse(
                        food.getFoodId(),
                        food.getPuppy() != null ? food.getPuppy().getPuppyId() : null,
                        food.getPuppy() != null ? food.getPuppy().getNickName() : null,
                        food.getMenu().getMenuName(),
                        food.getMenu().getImageURL(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getTime()
                ))
                .collect(Collectors.toList());
    }
}
