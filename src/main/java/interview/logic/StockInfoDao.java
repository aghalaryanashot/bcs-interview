package interview.logic;

import interview.dto.IexStockPriceDto;
import interview.dto.StocksDto;
import interview.dto.SymbolDto;
import interview.model.StockPriceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<IexStockPriceDto> list = new ArrayList<>();
        double value = 0;
        for (SymbolDto symbolDto : stocks.getStocks()) {
            IexStockPriceDto iexStockPriceDto =
                    restTemplate.getForEntity(API_URL, IexStockPriceDto.class, symbolDto.getSymbol()).getBody();
            iexStockPriceDto.setLatestPrice(iexStockPriceDto.getLatestPrice() * symbolDto.getVolume());
            value += iexStockPriceDto.getLatestPrice() * symbolDto.getVolume();
            if(list.contains(iexStockPriceDto)){
            }
            list.add(iexStockPriceDto);

        }
        StockPriceValue stockPriceValue = new StockPriceValue(value, list);
        return stockPriceValue;
    }
}
