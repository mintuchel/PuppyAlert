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
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.dto.response.MatchHistoryResponse;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PuppyService {
    private final FoodService foodService;
    private final FavoriteHostService favoriteHostService;

    private final PuppyRepository puppyRepository;

    // Puppy 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll() {
        return puppyRepository.findAll().stream()
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

    // Puppy 신청 가능한 집밥
    @Transactional(readOnly = true)
    public List<FoodInfoResponse> getAvailableFood(String puppyId){
        Puppy puppy = puppyRepository.findById(puppyId)
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));

        return foodService.getAvailableFood(puppy);
    }

    // Puppy 집밥 신청
    @Transactional
    public MatchResponse handleMatchRequest(MatchRequest matchRequest) {
        Long foodId = matchRequest.foodId();
        Puppy puppy = puppyRepository.findById(matchRequest.puppyId())
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));
        return foodService.handleMatchRequest(foodId, puppy);
    }

    // Puppy 집밥 기록 검색
    // 이건 READONLY 작업이므로 그냥 getFoodList 로 해주면 됨
    // 연관관계의 주인이 Puppy 가 아니긴 한데 CUD 작업이 아니므로
    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> getHistory(String puppyId) {
        Puppy puppy = puppyRepository.findById(puppyId).get();
        return puppy.getFoodList().stream()
                .map(food -> {
                    // 필요한 엔티티 미리 추출
                    // 참조할때마다 jpa join 쿼리 나가서 미리 해주는게 좋음
                    User curHost = food.getHost();
                    Menu curMenu = food.getMenu();

                    return new MatchHistoryResponse(
                            food.getFoodId(),
                            curHost.getId(),
                            curHost.getNickName(),
                            curMenu.getMenuName(),
                            curMenu.getImageURL(),
                            curHost.getAddress(),
                            curHost.getDetailAddress(),
                            curHost.getLocation(),
                            food.getTime(),
                            curHost.getProfileImageURL()
                    );
                })
                .collect(Collectors.toList());
    }


    // Puppy 관심 호스트 조회
    // readonly 작업이니까 그냥 list 들고와도댐
    // foodService 통해서 가장 최근시간 JPQL 로 확인
    @Transactional(readOnly = true)
    public List<FavoriteHostResponse> getFavoriteHost(String puppyId){
        Puppy puppy = puppyRepository.findById(puppyId)
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));

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
    public void addFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        favoriteHostService.addFavoriteHost(favoriteHostRequest);
    }

    // 관심 HOST 삭제
    @Transactional
    public void deleteFavoriteHost(FavoriteHostRequest favoriteHostRequest){
        favoriteHostService.deleteFavoriteHost(favoriteHostRequest);
    }
}
