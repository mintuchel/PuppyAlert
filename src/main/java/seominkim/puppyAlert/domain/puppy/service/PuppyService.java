package seominkim.puppyAlert.domain.puppy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.dto.response.FavoriteHostResponse;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
import seominkim.puppyAlert.domain.food.dto.response.FoodInfoResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.puppy.dto.request.EndMatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.dto.request.CancelFoodRequest;
import seominkim.puppyAlert.domain.user.dto.response.CancelFoodResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.UserException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PuppyService {
    private final FoodService foodService;
    private final FavoriteHostService favoriteHostService;

    private final UserRepository userRepository;

    // Puppy 전체 검색
    // stream() 이후에 filter로 PUPPY만 거르지 않고
    // nativequery를 하나 만들어 PUPPY인 것만 가져옴
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll() {
        return userRepository.findAllPuppy().stream()
                .map(puppy -> new UserInfoResponse(
                        puppy.getId(),
                        puppy.getName(),
                        puppy.getNickName(),
                        puppy.getBirth(),
                        puppy.getPhoneNumber(),
                        puppy.getAddress(),
                        puppy.getDetailAddress(),
                        puppy.getLocation(),
                        puppy.getProfileImageURL()
                ))
                .collect(Collectors.toList());
    }

    // Puppy 집밥 신청
    @Transactional
    public MatchResponse handleMatchRequest(MatchRequest matchRequest) {
        User puppy = userRepository.findPuppyById(matchRequest.puppyId())
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.handleMatchRequest(matchRequest.foodId(), puppy);
    }

    // Puppy 집밥 취소
    @Transactional
    public CancelFoodResponse handleCancelRequest(CancelFoodRequest cancelFoodRequest){
        User puppy = userRepository.findPuppyById(cancelFoodRequest.userId())
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.handleCancelFoodRequest(puppy, cancelFoodRequest);
    }

    // Puppy 집밥 완료
    @Transactional
    public Long handleEndMatchRequest(EndMatchRequest endMatchRequest){
        User puppy = userRepository.findPuppyById(endMatchRequest.puppyId())
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.handleEndMatchRequest(endMatchRequest.foodId());
    }

    // Puppy 신청 가능한 집밥
    @Transactional(readOnly = true)
    public List<FoodInfoResponse> getAvailableFood(String puppyId){
        User puppy = userRepository.findPuppyById(puppyId)
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return foodService.getAvailableFood(puppy);
    }

    // Puppy 관심 호스트 조회
    // readonly 작업이니까 그냥 list 들고와도댐
    // foodService 통해서 가장 최근시간 nativequery 로 확인
    @Transactional(readOnly = true)
    public List<FavoriteHostResponse> getFavoriteHost(String puppyId){
        User puppy = userRepository.findPuppyById(puppyId)
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTING_USER));

        return puppy.getFavoriteHostList().stream()
                .map(favoriteHost -> {
                    User curHost = favoriteHost.getHost();
                    String hostId = curHost.getId();
                    String hostNickName = curHost.getNickName();
                    String hostProfileImageUrl = curHost.getProfileImageURL();

                    Food recentFoodInfo = foodService.getMostRecentFood(puppyId, hostId);
                    LocalDateTime recentTime;

                    if(recentFoodInfo == null) recentTime = null;
                    else recentTime = recentFoodInfo.getTime();

                    FavoriteHostResponse favoriteHostResponse = new FavoriteHostResponse(
                            hostId,
                            hostNickName,
                            hostProfileImageUrl,
                            recentTime
                    );
                    return favoriteHostResponse;
                })
                .collect(Collectors.toList());
    }

    // 연관관계 주인이 FavoriteHost Entity 이므로
    // CRUD 작업은 FavoriteService(FavoriteRepository)에게 위임

    // 관심 HOST 추가
    @Transactional
    public Long addFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        return favoriteHostService.addFavoriteHost(favoriteHostRequest);
    }

    // 관심 HOST 삭제
    @Transactional
    public Long deleteFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        return favoriteHostService.deleteFavoriteHost(favoriteHostRequest);
    }
}
