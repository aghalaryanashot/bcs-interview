package interview.model;

import interview.dto.IexStockPriceDto;
import lombok.Data;

import java.util.List;

@Data
public class StockPriceValue {
    private double value;
    private List<IexStockPriceDto> listStockPrice;

    public StockPriceValue(double value, List<IexStockPriceDto> list) {
        this.value = value;
        this.listStockPrice = list;
    }
}
