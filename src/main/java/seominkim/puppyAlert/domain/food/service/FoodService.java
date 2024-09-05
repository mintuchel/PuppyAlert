package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.dto.response.FoodInfoResponse;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.service.MenuService;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.entity.User;
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
                            food.getStatus()
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
                            food.getStatus()
                    );
                })
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
    }

    @Transactional
    public AddFoodResponse addNewFood(User host, FoodRequest foodRequest){
        Food newFood = new Food();
        Menu menu = menuService.findOne(foodRequest.menuName());

        newFood.setHost(host);
        newFood.setMenu(menu);
        newFood.setTime(foodRequest.time());
        newFood.setStatus(foodRequest.status());

        // save 되면서 @Id @GeneratedValue 값이 생성됨
        // food와 menu의 관계 중 food가 연관관계의 주인이기 때문에
        // foodRepository.save 되면서 menu도 저장이 됨
        foodRepository.save(newFood);

        return new AddFoodResponse(newFood.getFoodId(), menu.getImageURL());
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
                            food.getStatus()
                    );
                })
                .collect(Collectors.toList());

    }

    @Transactional
    public MatchResponse handleMatchRequest(Long foodId, User puppy) {
        Food matchedFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));

        if(matchedFood.getStatus().equals(FoodStatus.MATCHED)){
            throw new FoodException(ErrorCode.ALREADY_MATCHED);
        }

        // 집밥 업데이트
        matchedFood.setPuppy(puppy);
        matchedFood.setStatus(FoodStatus.MATCHED);

        return new MatchResponse(
                matchedFood.getFoodId(),
                // 이거 뺼까? 차피 사용안하는데?? 내일 꼭 물어보기
                // 이거 하려고 또 join 쿼리 나가서 성능 안좋아짐
                matchedFood.getHost().getNickName(),
                puppy.getId()
        );
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
