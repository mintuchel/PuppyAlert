package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.food.dto.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.dto.MatchHistoryResponse;
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
    private final HostRepository hostRepository;
    private final PuppyRepository puppyRepository;

    @Transactional
    public Long add(FoodRequest foodRequest){
        Host providerHost = hostRepository.findById(foodRequest.hostId()).orElseThrow(() -> new RuntimeException("Host not found"));

        Food food = new Food();
        food.setHost(providerHost);
        food.setTime(foodRequest.time());
        food.setStatus(foodRequest.status());
        food.setMenu(foodRequest.menu());

        foodRepository.save(food);
        return food.getFoodId();
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
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FoodResponse> findAvailable(String puppyId){
        List<Food> foodList = foodRepository.findAll();
        Puppy puppy = puppyRepository.findById(puppyId).orElseThrow(() -> new RuntimeException("Puppy not found"));

        Double curPuppyLatitude = puppy.getLocation().getLatitude();
        Double curPuppyLongitude = puppy.getLocation().getLongitude();

        List<Food> availableFoodList = LocationBasedSearch.findFoodWithinRange(curPuppyLatitude, curPuppyLongitude, foodList, 500);

        return availableFoodList.stream()
                .map(food -> new FoodResponse(
                        food.getFoodId(),
                        food.getHost().getHostId(),
                        food.getMenu(),
                        food.getTime(),
                        food.getImageURL(),
                        food.getHost().getAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FoodResponse findById(Long zipbobId) {
        return foodRepository.findById(zipbobId)
                .map(food -> new FoodResponse(
                        food.getFoodId(),
                        food.getHost().getHostId(),
                        food.getMenu(),
                        food.getTime(),
                        food.getImageURL(),
                        food.getHost().getAddress(),
                        food.getHost().getLocation(),
                        food.getStatus()
                ))
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
    }

    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> findHostHistory(String hostId){
        return foodRepository.findByHost_HostId(hostId).stream()
                .map(zipbob -> new MatchHistoryResponse(
                        zipbob.getPuppy().getPuppyId(),
                        zipbob.getMenu(),
                        zipbob.getTime()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> findPuppyHistory(String puppyId){
        return foodRepository.findByPuppy_PuppyId(puppyId).stream()
                .map(zipbob -> new MatchHistoryResponse(
                        zipbob.getHost().getHostId(),
                        zipbob.getMenu(),
                        zipbob.getTime()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Food> findFavoriteHostHistory(String puppyId, String hostId){
        return foodRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId);
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
