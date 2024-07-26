package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.FoodRequest;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.FoodException;
import seominkim.puppyAlert.global.exception.exception.HostException;
import seominkim.puppyAlert.global.utils.LocationBasedSearch;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public Long addNewFood(Host providerHost, FoodRequest foodRequest){
        Food newFood = new Food();
        newFood.setHost(providerHost);
        newFood.setTime(foodRequest.time());
        newFood.setStatus(foodRequest.status());
        newFood.setMenu(foodRequest.menu());

        // save 되면서 @Id @GeneratedValue 값이 생성됨
        foodRepository.save(newFood);
        return newFood.getFoodId();
    }

    @Transactional(readOnly = true)
    public List<FoodResponse> findAll(){
        return foodRepository.findAll().stream()
                .map(food -> new FoodResponse(
                        food.getFoodId(),
                        food.getHost().getHostId(),
                        food.getMenu(),
                        food.getTime(),
                        food.getImageURL(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FoodResponse> getAvailableFood(Location location){
        List<Food> foodList = foodRepository.findAll();

        Double curPuppyLatitude = location.getLatitude();
        Double curPuppyLongitude = location.getLongitude();

        List<Food> availableFoodList = LocationBasedSearch.findFoodWithinRange(curPuppyLatitude, curPuppyLongitude, foodList, 500);

        return availableFoodList.stream()
                .map(food -> new FoodResponse(
                        food.getFoodId(),
                        food.getHost().getHostId(),
                        food.getMenu(),
                        food.getTime(),
                        food.getImageURL(),
                        food.getHost().getAddress(),
                        food.getHost().getDetailAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FoodResponse findById(Long foodId) {
        return foodRepository.findById(foodId)
                .map(food -> new FoodResponse(
                        food.getFoodId(),
                        food.getHost().getHostId(),
                        food.getMenu(),
                        food.getTime(),
                        food.getImageURL(),
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

        // 집밥 업데이트
        matchedFood.setPuppy(puppy);
        matchedFood.setStatus(FoodStatus.MATCHED);

        return new MatchResponse(
                matchedFood.getFoodId(),
                matchedFood.getHost().getHostId(),
                puppy.getPuppyId()
        );
    }

    @Transactional(readOnly = true)
    public Food getMostRecentFood(String puppyId, String hostId){
        List<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId, PageRequest.of(0, 1));
        if (mostRecentFood.isEmpty()) {
            throw new HostException(ErrorCode.NO_RECENT_MATCH);
        } else {
            return mostRecentFood.get(0);
        }
    }
}
