package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.dto.response.MatchHistoryResponse;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.HostException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostService {
    private final FoodService foodService;

    private final UserRepository userRepository;

    // 집밥 추가
    @Transactional
    public AddFoodResponse addFood(FoodRequest foodRequest){
        User host = userRepository.findById(foodRequest.hostId())
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        return foodService.addNewFood(host, foodRequest);
    }

    // Host 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.HOST) // UserType이 Host인 경우만 필터링
                .map(user -> {
                    UserInfoResponse dto = new UserInfoResponse(
                            user.getId(),
                            user.getName(),
                            user.getNickName(),
                            user.getBirth(),
                            user.getPhoneNumber(),
                            user.getAddress(),
                            user.getDetailAddress(),
                            user.getLocation(),
                            user.getProfileImageURL()
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Host 집밥 기록 검색
    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> getHistory(String hostId){
        User host = userRepository.findById(hostId)
                .orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));

        // Host 엔티티에서 hostFoods 추출해서 사용
        return host.getHostFoods().stream()
                .map(food -> {
                    // 필요한 엔티티 미리 추출
                    // 참조할때마다 jpa join 쿼리 나가서 미리 해주는게 좋음
                    User puppy = food.getPuppy();
                    Menu menu = food.getMenu();

                    return new MatchHistoryResponse(
                        food.getFoodId(),
                        puppy != null ? puppy.getId() : null,
                        puppy != null ? puppy.getNickName() : null,
                        menu.getMenuName(),
                        menu.getImageURL(),
                        host.getAddress(),
                        host.getDetailAddress(),
                        host.getLocation(),
                        food.getTime(),
                        puppy.getProfileImageURL()
                    );
                })
                .collect(Collectors.toList());
    }
}
