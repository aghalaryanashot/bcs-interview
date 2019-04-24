package interview.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class IexStockPriceDto {
    private String sector;
    private double latestPrice;

    public IexStockPriceDto(String sector, double latestPrice) {
        this.sector = sector;
        this.latestPrice = latestPrice;
    }
}
