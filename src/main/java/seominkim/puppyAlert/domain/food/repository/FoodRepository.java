package seominkim.puppyAlert.domain.food.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.food.entity.Food;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    // JPQL 에는 LIMIT 절이 없으므로 Pageable 로 해결해야함
    @Query("SELECT z FROM Food z WHERE z.puppy.id = :puppyId AND z.host.id = :hostId ORDER BY z.time DESC")
    List<Food> findMostRecentByPuppyIdAndHostId(@Param("puppyId") String puppyId, @Param("hostId") String hostId, Pageable pageable);
}
