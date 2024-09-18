package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
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
import seominkim.puppyAlert.global.exception.exception.UserException;
import seominkim.puppyAlert.global.utility.FoodLimitator;

import java.util.List;
import java.util.Optional;
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
                .orElseThrow(() -> new FoodException(ErrorCode.NOT_EXISTING_FOOD));
    }

    @Transactional
    public AddFoodResponse handleAddFoodRequest(User host, AddFoodRequest addFoodRequest){
        Menu menu = menuService.getMenu(addFoodRequest.menuName());

        Food newFood = Food.builder()
                .host(host)
                .menu(menu)
                .time(addFoodRequest.time())
                .matchStatus(MatchStatus.READY)
                .build();

        // cascade에 menu도 저장됨
        // 만약 기존 DB에 존재하면 저장안하고 연관관계만 저장
        // 아니면 새로운 menu 엔티티까지 저장
        foodRepository.save(newFood);

        return new AddFoodResponse(newFood.getFoodId(), menu.getImageURL());
    }

    @Transactional
    public CancelFoodResponse handleCancelFoodRequest(User user, CancelFoodRequest cancelFoodRequest){
        long cancelFoodId = cancelFoodRequest.foodId();

        Food cancelingFood = foodRepository.findById(cancelFoodId)
                .orElseThrow(()->new FoodException(ErrorCode.NOT_EXISTING_FOOD));

//        // 해당 집밥과 연관없으면 예외처리
//        if(user.getId() != cancelingFood.getPuppy().getId() && user.getId() != cancelingFood.getHost().getId()){
//            throw new UserException(ErrorCode.UNAUTORIZED_REQUEST);
//        }
//
//        if(cancelingFood.getHost().getId()!=user.getId()){
//            if(cancelingFood.getPuppy() == null || cancelingFood.getPuppy().getId()!=user.getId())
//        }

        if(cancelingFood.getMatchStatus() == MatchStatus.COMPLETE) throw new FoodException(ErrorCode.ALREADY_COMPLETED);

        if(user.getUserType() == UserType.HOST) {
            // foodRepository 삭제하는거해야함
            foodRepository.delete(cancelingFood);
        }else{
            // Transactional 덕에 끝나면 변경 상태 자동 반영
            cancelingFood.changePuppy(null);
            cancelingFood.changeMatchStatus(MatchStatus.READY);
        }

        return new CancelFoodResponse(cancelFoodId);
    }

    @Transactional
    public MatchResponse handleMatchRequest(Long foodId, User puppy) {
        Food matchedFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodException(ErrorCode.NOT_EXISTING_FOOD));

        MatchStatus curFoodStatus = matchedFood.getMatchStatus();

        if(curFoodStatus == MatchStatus.MATCHED){
            throw new FoodException(ErrorCode.ALREADY_MATCHED);
        }

        if(curFoodStatus == MatchStatus.COMPLETE){
            throw new FoodException(ErrorCode.ALREADY_COMPLETED);
        }

        // 집밥 업데이트
        matchedFood.changePuppy(puppy);
        matchedFood.changeMatchStatus(MatchStatus.MATCHED);

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
                .orElseThrow(() -> new FoodException(ErrorCode.NOT_EXISTING_FOOD));

        // 아직 매칭되지 않은 집밥이면
        if(matchedFood.getMatchStatus() == MatchStatus.READY){
            throw new FoodException(ErrorCode.NOT_MATCHED_FOOD);
        }

        // 이미 식사가 끝난 집밥이면
        if(matchedFood.getMatchStatus() == MatchStatus.COMPLETE){
            throw new FoodException(ErrorCode.ALREADY_COMPLETED);
        }

        matchedFood.changeMatchStatus(MatchStatus.COMPLETE);

        return foodId;
    }

    @Transactional(readOnly = true)
    public List<FoodInfoResponse> getAvailableFood(User puppy){

        Double curPuppyLatitude = puppy.getLocation().getLatitude();
        Double curPuppyLongitude = puppy.getLocation().getLongitude();

        List<Food> foodList = foodRepository.findAll();

        List<Food> availableFoodList = foodLimitator.findFoodWithinPuppyRange(curPuppyLatitude, curPuppyLongitude, foodList);

        return availableFoodList.stream()
                .filter(food -> food.getMatchStatus() != MatchStatus.COMPLETE)
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

    @Transactional(readOnly = true)
    public Food getMostRecentFood(String puppyId, String hostId){
        // JPA는 limit 구문 지원안해서 이런 방식으로 해야함
        // .get(0)하는게 없어보이긴하다
        // List<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId, PageRequest.of(0, 1));

        Optional<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId);

        if(mostRecentFood.isPresent()) {
            return mostRecentFood.get();
        }else{
            return null;
        }
    }
}
