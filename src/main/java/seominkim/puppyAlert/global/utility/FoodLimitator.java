package seominkim.puppyAlert.global.utility;

import org.springframework.stereotype.Component;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.market.entity.Market;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodLimitator {

    // 지구 반지름 (미터 단위)
    private static final double EARTH_RADIUS = 6371000;
    // 위도 1도당 미터 수 (고정 값)
    private static final double LATITUDE_METER = 111000;

    private static final double TARGET_RANGE_METER = 500;

    public List<Food> findFoodWithinPuppyRange(double currentLatitude, double currentLongitude, List<Food> foodList) {
        List<Food> foodWithinRange = new ArrayList<>();

        for (Food food : foodList) {
            Double curFoodLatitude = food.getHost().getLocation().getLatitude();
            Double curFoodLongitude = food.getHost().getLocation().getLongitude();

            if (isWithinRadius(currentLatitude, currentLongitude, curFoodLatitude, curFoodLongitude, TARGET_RANGE_METER)) {
                foodWithinRange.add(food);
            }
        }

        return foodWithinRange;
    }

    public boolean isWithinRadius(double latitude, double longitude, double targetLatitude, double targetLongitude, double radiusMeters) {
        // 위도와 경도 차이 계산
        double latDifference = radiusMeters / LATITUDE_METER;
        double lonDifference = radiusMeters / (LATITUDE_METER * Math.cos(Math.toRadians(latitude)));

        // 위도와 경도의 범위 계산
        double minLatitude = latitude - latDifference;
        double maxLatitude = latitude + latDifference;
        double minLongitude = longitude - lonDifference;
        double maxLongitude = longitude + lonDifference;

        // 대상 지점이 범위 내에 있는지 확인
        return (minLatitude <= targetLatitude && targetLatitude <= maxLatitude) &&
                (minLongitude <= targetLongitude && targetLongitude <= maxLongitude);
    }
}
