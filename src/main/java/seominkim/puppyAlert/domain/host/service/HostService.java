package seominkim.puppyAlert.domain.host.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.dto.request.AddFoodRequest;
import seominkim.puppyAlert.domain.user.dto.request.CancelFoodRequest;
import seominkim.puppyAlert.domain.host.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.user.dto.response.CancelFoodResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.UserException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostService {
    private final FoodService foodService;

    private final UserRepository userRepository;

    // 집밥 추가
    @Transactional
    public AddFoodResponse handleAddFoodRequest(AddFoodRequest addFoodRequest){
        User host = userRepository.findHostById(addFoodRequest.hostId())
                .orElseThrow(()->new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.handleAddFoodRequest(host, addFoodRequest);
    }

    // 집밥 취소
    @Transactional
    public CancelFoodResponse handleCancelFoodRequest(CancelFoodRequest cancelFoodRequest){
        User host = userRepository.findHostById(cancelFoodRequest.userId())
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.handleCancelFoodRequest(host, cancelFoodRequest);
    }
    
    // Host 전체 검색
    // findAll을 사용한 후에 filter로 HOST 거르는 작업을 하지 않고
    // nativequery를 사용하여 한번에 HOST 칼럼만 가져옴
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll() {
        return userRepository.findAllHost().stream()
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
}
