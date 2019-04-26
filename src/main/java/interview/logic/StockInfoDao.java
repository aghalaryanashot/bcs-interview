package interview.logic;

import interview.dto.IexStockPriceDto;
import interview.dto.StocksDto;
import interview.dto.SymbolDto;
import interview.model.StockPriceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class StockInfoDao {
    private final static String API_URL = "https://api.iextrading.com/1.0/stock/{symbol}/quote";
    private RestTemplate restTemplate;

    @Autowired
    public StockInfoDao(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StockPriceValue getStockPrice(StocksDto stocks) {
        double value = 0;
        Map<String, Double> sectorToValue = new HashMap<>();
        for (SymbolDto symbolDto : stocks.getStocks()) {
            IexStockPriceDto iexStockPriceDto =
                    restTemplate.getForEntity(API_URL, IexStockPriceDto.class, symbolDto.getSymbol()).getBody();
            assert iexStockPriceDto != null;
            iexStockPriceDto.setLatestPrice(iexStockPriceDto.getLatestPrice() * symbolDto.getVolume());
            value += iexStockPriceDto.getLatestPrice() * symbolDto.getVolume();
            sectorToValue.compute(iexStockPriceDto.getSector(), (k, v) -> (v == null ? 0 : v) +
                    iexStockPriceDto.getLatestPrice() * symbolDto.getVolume());
        }
        return new StockPriceValue(value, sectorToValue);
    }
}
