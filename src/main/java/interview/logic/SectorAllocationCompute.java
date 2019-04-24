package interview.logic;

import interview.Dto.*;
import interview.RestBean;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;

public class SectorAllocationCompute {
    private final static String API_URL = "https://api.iextrading.com/1.0/stock/{symbol}/quote";
    private RestTemplate restTemplate;

    public SectorAllocationCompute(RestTemplate restTemplate) {
        this.restTemplate = RestBean.restTemplate();
    }

    public ValueAndAllocationsDto compute(StocksDto stocks) {
        return compute(stocks, name ->
                restTemplate.getForEntity(API_URL, IexStockPriceDto.class, name).getBody()
        );
    }

    public static ValueAndAllocationsDto compute(StocksDto stocks, Function<String, IexStockPriceDto> stockGetter) {
        ArrayList<AllocationsDto> allocationsList = new ArrayList<>();
        double value = 0;
        Map<String, Double> sectorToValue = new HashMap<>();
        for (SymbolDto symbol : stocks.getStocks()) {
            IexStockPriceDto iexStock = stockGetter.apply(symbol.getSymbol());
            double totalPrice = iexStock.getLatestPrice() * symbol.getVolume();
            sectorToValue.compute(iexStock.getSector(), (k, v) -> (v == null ? 0 : v) + totalPrice);
            value += totalPrice;
        }
        for (Map.Entry<String, Double> sectorAndPrice : sectorToValue.entrySet()) {
            String sector = sectorAndPrice.getKey();
            double price = sectorAndPrice.getValue();
            double proportion = sectorAndPrice.getValue() / value;
            allocationsList.add(new AllocationsDto(sector, price, proportion));
        }
        return new ValueAndAllocationsDto(value, allocationsList);
    }
}
