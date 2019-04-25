package interview.model;

import interview.dto.IexStockPriceDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StockPriceValue {
    private double value;
    private Map<String,Double> map;

    public StockPriceValue(double value, Map<String,Double> map) {
        this.value = value;
        this.map = map;
    }
}
