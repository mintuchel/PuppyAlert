package seominkim.puppyAlert.domain.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.market.entity.Market;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {
    @Query("SELECT m FROM Market m WHERE m.name LIKE '%:region%'")
    List<Market> findMarketsByRegion(@Param("region") String region);
}
