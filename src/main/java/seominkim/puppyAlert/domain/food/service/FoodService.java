package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
import seominkim.puppyAlert.domain.food.entity.DiningStatus;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.host.dto.request.AddFoodRequest;
import seominkim.puppyAlert.domain.user.dto.request.CancelFoodRequest;
import seominkim.puppyAlert.domain.host.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.dto.response.FoodInfoResponse;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.domain.user.dto.response.CancelFoodResponse;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.service.MenuService;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.FoodException;
import seominkim.puppyAlert.global.utility.FoodLimitator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final MenuService menuService;
    private final FavoriteHostService favoriteHostService;

    private final FoodRepository foodRepository;

    private final FoodLimitator foodLimitator;

    @Transactional(readOnly = true)
    public List<FoodInfoResponse> findAll(){
        return foodRepository.findAll().stream()
                .map(food -> {
                    User host = food.getHost();
                    Menu menu = food.getMenu();

                    return new FoodInfoResponse(
                            food.getFoodId(),
                            host.getId(),
                            host.getNickName(),
                            false,
                            menu.getMenuName(),
                            menu.getImageURL(),
                            food.getTime(),
                            host.getAddress(),
                            host.getDetailAddress(),
                            host.getLocation(),
                            food.getMatchStatus()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FoodInfoResponse findById(Long foodId) {
        return foodRepository.findById(foodId)
                .map(food -> {
                    User host = food.getHost();
                    Menu menu = food.getMenu();

                    return new FoodInfoResponse(
                            food.getFoodId(),
                            host.getId(),
                            host.getNickName(),
                            false,
                            menu.getMenuName(),
                            menu.getImageURL(),
                            food.getTime(),
                            host.getAddress(),
                            host.getDetailAddress(),
                            host.getLocation(),
                            food.getMatchStatus()
                    );
                })
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
    }

    @Transactional
    public AddFoodResponse addNewFood(User host, AddFoodRequest addFoodRequest){
        Food newFood = new Food();
        Menu menu = menuService.getMenu(addFoodRequest.menuName());

        newFood.setHost(host);
        newFood.setMenu(menu);
        newFood.setTime(addFoodRequest.time());
        // status setting
        newFood.setMatchStatus(MatchStatus.READY);
        newFood.setDiningStatus(DiningStatus.PENDING);

        // save 되면서 @Id @GeneratedValue 값이 생성됨
        // food와 menu의 관계 중 food가 연관관계의 주인이기 때문에
        // foodRepository.save 되면서 menu도 저장이 됨
        foodRepository.save(newFood);

        return new AddFoodResponse(newFood.getFoodId(), menu.getImageURL());
    }

    @Transactional
    public CancelFoodResponse cancelFood(CancelFoodRequest cancelFoodRequest, UserType type){
        long cancelFoodId = cancelFoodRequest.foodId();

        Food cancelingFood = foodRepository.findById(cancelFoodId)
                .orElseThrow(()->new FoodException(ErrorCode.NON_EXISTING_FOOD));

        if(type==UserType.HOST) {
            // foodRepository 삭제하는거해야함
            foodRepository.delete(cancelingFood);
        }else{
            // Transactional 덕에 끝나면 변경 상태 자동 반영
            cancelingFood.setPuppy(null);
            cancelingFood.setMatchStatus(MatchStatus.READY);
            cancelingFood.setDiningStatus(DiningStatus.PENDING);
        }

        return new CancelFoodResponse(cancelFoodId);
    }

    @Transactional(readOnly = true)
    public List<FoodInfoResponse> getAvailableFood(User puppy){

        Double curPuppyLatitude = puppy.getLocation().getLatitude();
        Double curPuppyLongitude = puppy.getLocation().getLongitude();

        List<Food> foodList = foodRepository.findAll();

        List<Food> availableFoodList = foodLimitator.findFoodWithinPuppyRange(curPuppyLatitude, curPuppyLongitude, foodList);

        return availableFoodList.stream()
                .map(food -> {
                    User curHost = food.getHost();
                    Menu menu = food.getMenu();
                    boolean isFavorite = favoriteHostService.isFavoriteHost(puppy.getId(), curHost.getId());

                    return new FoodInfoResponse(
                            food.getFoodId(),
                            curHost.getId(),
                            curHost.getNickName(),
                            isFavorite,
                            menu.getMenuName(),
                            menu.getImageURL(),
                            food.getTime(),
                            curHost.getAddress(),
                            curHost.getDetailAddress(),
                            curHost.getLocation(),
                            food.getMatchStatus()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public MatchResponse handleMatchRequest(Long foodId, User puppy) {
        Food matchedFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));

        if(matchedFood.getMatchStatus().equals(MatchStatus.MATCHED)){
            throw new FoodException(ErrorCode.ALREADY_MATCHED);
        }

        // 집밥 업데이트
        matchedFood.setPuppy(puppy);
        matchedFood.setMatchStatus(MatchStatus.MATCHED);

        return new MatchResponse(
                matchedFood.getFoodId(),
                // 이거 뺼까? 차피 사용안하는데?? 내일 꼭 물어보기
                // 이거 하려고 또 join 쿼리 나가서 성능 안좋아짐
                matchedFood.getHost().getNickName(),
                puppy.getId()
        );
    }

    @Transactional
    public Long handleEndMatchRequest(Long foodId){
        Food matchedFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));

        // 아직 매칭되지 않은 집밥이면
        if(matchedFood.getMatchStatus() == MatchStatus.READY){
            throw new FoodException(ErrorCode.NOT_MATCHED_FOOD);
        }

        // 이미 식사가 끝난 집밥이면
        if(matchedFood.getDiningStatus() == DiningStatus.COMPLETE){
            throw new FoodException(ErrorCode.ALREADY_FINISHED_DINING);
        }

        matchedFood.setDiningStatus(DiningStatus.COMPLETE);

        return foodId;
    }

    @Transactional(readOnly = true)
    public Food getMostRecentFood(String puppyId, String hostId){
        // JPA는 limit 구문 지원안해서 이런 방식으로 해야함
        // .get(0)하는게 없어보이긴하다
        List<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId, PageRequest.of(0, 1));

        if(mostRecentFood.isEmpty()) return null;

        return mostRecentFood.get(0);
    }
}
