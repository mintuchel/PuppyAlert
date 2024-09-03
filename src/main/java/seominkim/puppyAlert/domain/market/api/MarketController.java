package seominkim.puppyAlert.domain.market.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.domain.market.dto.response.MarketResponse;
import seominkim.puppyAlert.domain.market.service.MarketService;
import seominkim.puppyAlert.domain.shop.dto.response.ShopResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/market")
@Tag(name = "Market API", description = "전통시장 조회, 전통시장 내 가맹점 조회")
public class MarketController {

    private final MarketService marketService;

    @Operation(summary = "지역구 내 전통시장 조회")
    @GetMapping("")
    public List<MarketResponse> getMarketsByRegion(@RequestParam String region){
        return marketService.getMarketsByRegion(region);
    }

    @Operation(summary = "전체 조회")
    @GetMapping("/all")
    public List<MarketResponse> getAllMarkets(){
        return marketService.findAll();
    }

    @Operation(summary = "전통시장 내 가맹점 조회")
    @GetMapping("/shops")
    public List<ShopResponse> getShopsByMarket(@RequestParam int marketId){
        return marketService.getShopsByMarket(marketId);
    }
}
