package seominkim.puppyAlert.domain.puppy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponse;
import seominkim.puppyAlert.global.dto.UserInfoResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.FoodException;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;
    private final FoodRepository foodRepository;

    // Puppy 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll() {
        return puppyRepository.findAll().stream()
                .map(puppy -> new UserInfoResponse(
                        puppy.getPuppyId(),
                        puppy.getName(),
                        puppy.getNickName(),
                        puppy.getBirth(),
                        puppy.getPhoneNumber(),
                        puppy.getAddress(),
                        puppy.getLocation()
                ))
                .collect(Collectors.toList());
    }

    // Puppy 단건 검색
    @Transactional(readOnly = true)
    public UserInfoResponse findById(String puppyId) {
        return puppyRepository.findById(puppyId)
                .map(puppy -> new UserInfoResponse(
                        puppy.getPuppyId(),
                        puppy.getName(),
                        puppy.getNickName(),
                        puppy.getBirth(),
                        puppy.getPhoneNumber(),
                        puppy.getAddress(),
                        puppy.getLocation()
                ))
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));
    }

    @Transactional
    public MatchResponse matchFood(MatchRequest matchRequest) {
        Food food = foodRepository.findById(matchRequest.foodId())
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
        Puppy puppy = puppyRepository.findById(matchRequest.puppyId())
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));

        // 집밥 업데이트
        food.setPuppy(puppy);
        food.setStatus(FoodStatus.MATCHED);

        // 업데이트된 집밥 저장
        foodRepository.save(food);

        return new MatchResponse(
                food.getFoodId(),
                food.getHost().getHostId(),
                puppy.getPuppyId()
        );
    }
}
