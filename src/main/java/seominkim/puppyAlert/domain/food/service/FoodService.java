package seominkim.puppyAlert.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.food.dto.FoodRequestDTO;
import seominkim.puppyAlert.domain.food.dto.FoodResponseDTO;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.global.dto.MatchHistoryResponseDTO;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.FoodException;
import seominkim.puppyAlert.global.exception.exception.HostException;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final HostRepository hostRepository;

    @Transactional
    public Long add(FoodRequestDTO foodRequestDTO){
        Host providerHost = hostRepository.findById(foodRequestDTO.getHostId()).get();

        Food food = new Food();

        food.setHost(providerHost);
        food.setTime(foodRequestDTO.getTime());
        food.setStatus(foodRequestDTO.getStatus());
        food.setMenu(foodRequestDTO.getMenu());

        foodRepository.save(food);
        return food.getFoodId();
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> findAll(){
        List<FoodResponseDTO> availableFoodList = foodRepository.findAll().stream()
                .map(food -> {
                    FoodResponseDTO dto = new FoodResponseDTO();
                    dto.setHostId(food.getHost().getHostId());
                    dto.setMenu(food.getMenu());
                    dto.setTime(food.getTime());
                    dto.setAddress(food.getHost().getAddress());
                    dto.setLocation(food.getHost().getLocation());
                    dto.setStatus(food.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());

        return availableFoodList;
    }

    @Transactional(readOnly = true)
    public FoodResponseDTO findById(Long zipbobId) {
        return foodRepository.findById(zipbobId)
                .map(food -> {
                    FoodResponseDTO dto = new FoodResponseDTO();
                    dto.setFoodId(food.getFoodId());
                    dto.setHostId(food.getHost().getHostId());
                    dto.setAddress(food.getHost().getAddress());
                    dto.setLocation(food.getHost().getLocation());
                    dto.setStatus(food.getStatus());
                    dto.setTime(food.getTime());
                    dto.setMenu(food.getMenu());
                    return dto;
                })
                .orElseThrow(() -> new FoodException(ErrorCode.NON_EXISTING_FOOD));
    }


    @Transactional(readOnly = true)
    public List<MatchHistoryResponseDTO> findHostHistory(String hostId){

        List<MatchHistoryResponseDTO> hostHistoryList = foodRepository.findByHost_HostId(hostId).stream()
                .map(zipbob -> {
                    MatchHistoryResponseDTO dto = new MatchHistoryResponseDTO();
                    // Host 에게는 Partner 가 Puppy 임
                    dto.setPartnerId(zipbob.getPuppy().getPuppyId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setLocalDateTime(zipbob.getTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return hostHistoryList;
    }

    @Transactional(readOnly = true)
    public List<MatchHistoryResponseDTO> findPuppyHistory(String puppyId){

        List<MatchHistoryResponseDTO> puppyHistoryList = foodRepository.findByPuppy_PuppyId(puppyId).stream()
                .map(zipbob -> {
                    MatchHistoryResponseDTO dto = new MatchHistoryResponseDTO();
                    // Puppy 에게는 Partner 가 Host 임
                    dto.setPartnerId(zipbob.getHost().getHostId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setLocalDateTime(zipbob.getTime());
                    return dto;
                })
                .collect(Collectors.toList());
        return puppyHistoryList;
    }

    @Transactional(readOnly = true)
    public List<Food> findFavoriteHostHistory(String puppyId, String hostId){
        return foodRepository.findByPuppy_PuppyIdAndHost_HostId(puppyId, hostId);
    }

    @Transactional(readOnly = true)
    public Food getMostRecentFood(String puppyId, String hostId){
        List<Food> mostRecentFood = foodRepository.findMostRecentByPuppyIdAndHostId(puppyId, hostId, PageRequest.of(0,1));
        if(mostRecentFood.isEmpty()){
            throw new HostException(ErrorCode.NO_RECENT_MATCH);
        }else{
            return mostRecentFood.get(0);
        }
    }
}