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
import seominkim.puppyAlert.global.entity.UserType;
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
        User host = userRepository.findById(addFoodRequest.hostId())
                .orElseThrow(() -> new UserException(ErrorCode.NON_EXISTING_USER));

        return foodService.addNewFood(host, addFoodRequest);
    }

    // 집밥 취소
    @Transactional
    public CancelFoodResponse handleCancelFoodRequest(CancelFoodRequest cancelFoodRequest){
        User host = userRepository.findById(cancelFoodRequest.userId())
                .orElseThrow(() -> new UserException(ErrorCode.NON_EXISTING_USER));

        if(host.getUserType() == UserType.PUPPY) throw new UserException(ErrorCode.INVALID_USERTYPE);

        return foodService.cancelFood(cancelFoodRequest, UserType.HOST);
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
}
