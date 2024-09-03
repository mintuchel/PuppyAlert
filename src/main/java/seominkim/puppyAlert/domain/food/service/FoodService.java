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
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.menu.service.MenuService;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
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

    @Transactional
    public AddFoodResponse addNewFood(Host providerHost, FoodRequest foodRequest){
        Food newFood = new Food();
        newFood.setHost(providerHost);
        newFood.setMenu(menuService.findOne(foodRequest.menuName()));
        newFood.setTime(foodRequest.time());
        newFood.setStatus(foodRequest.status());

        // save 되면서 @Id @GeneratedValue 값이 생성됨
        // food와 menu의 관계 중 food가 연관관계의 주인이기 때문에
        // foodRepository.save 되면서 menu도 저장이 됨
        foodRepository.save(newFood);

        return new AddFoodResponse(newFood.getFoodId(), newFood.getMenu().getImageURL());
    }

    @Transactional(readOnly = true)
    public List<FoodInfoResponse> findAll(){
        return foodRepository.findAll().stream()
                .map(food -> new FoodInfoResponse(
                        food.getFoodId(),
                        food.getHost().getId(),
                        food.getHost().getNickName(),
                        false,
                        food.getMenu().getMenuName(),
                        food.getMenu().getImageURL(),
                        food.getTime(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FoodInfoResponse> getAvailableFood(Puppy puppy){

        Double curPuppyLatitude = puppy.getLocation().getLatitude();
        Double curPuppyLongitude = puppy.getLocation().getLongitude();

        List<Food> foodList = foodRepository.findAll();

        List<Food> availableFoodList = foodLimitator.findFoodWithinPuppyRange(curPuppyLatitude, curPuppyLongitude, foodList);

        return availableFoodList.stream()
                .map(food -> new FoodInfoResponse(
                        food.getFoodId(),
                        food.getHost().getId(),
                        food.getHost().getNickName(),
                        favoriteHostService.isFavoriteHost(puppy.getId(), food.getHost().getId()),
                        food.getMenu().getMenuName(),
                        food.getMenu().getImageURL(),
                        food.getTime(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FoodInfoResponse findById(Long foodId) {
        return foodRepository.findById(foodId)
                .map(food -> new FoodInfoResponse(
                        food.getFoodId(),
                        food.getHost().getId(),
                        food.getHost().getNickName(),
                        false,
                        food.getMenu().getMenuName(),
                        food.getMenu().getImageURL(),
                        food.getTime(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
    }

    @Transactional
    public MatchResponse handleMatchRequest(Long foodId, Puppy puppy) {
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
                matchedFood.getHost().getNickName(),
                puppy.getId()
        );
    }

    @Transactional(readOnly = true)
    public Food getMostRecentFood(String puppyId, String hostId){
        List<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId, PageRequest.of(0, 1));

        if(mostRecentFood.isEmpty()) return null;

        return mostRecentFood.get(0);
    }
}
