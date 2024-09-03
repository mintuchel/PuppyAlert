package seominkim.puppyAlert.domain.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.market.dto.response.MarketResponse;
import seominkim.puppyAlert.domain.market.entity.Market;
import seominkim.puppyAlert.domain.market.repository.MarketRepository;
import seominkim.puppyAlert.domain.shop.dto.response.ShopResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    @Transactional(readOnly = true)
    public List<MarketResponse> findAll(){
        return marketRepository.findAll().stream()
                .map(market -> new MarketResponse(
                        market.getId(),
                        market.getName(),
                        market.getDetailAddress(),
                        market.getLocation(),
                        market.getImageURL()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MarketResponse> getMarketsByRegion(String region){
        List<Market> markets = marketRepository.findMarketsByRegion(region);

        return markets.stream()
                .map(market -> new MarketResponse(
                        market.getId(),
                        market.getName(),
                        market.getDetailAddress(),
                        market.getLocation(),
                        market.getImageURL()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ShopResponse> getShopsByMarket(int marketId){
        Market market = marketRepository.findById(marketId).orElseThrow();

        return market.getShopList().stream()
                .map(shop -> new ShopResponse(
                        shop.getShopName(),
                        shop.getDetailAddress(),
                        shop.getProductType().getDescription()
                ))
                .collect(Collectors.toList());
    }
}
