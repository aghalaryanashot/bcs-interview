package interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class IexStockPriceDto {
    private String sector;
    private double latestPrice;

    public IexStockPriceDto(String sector, double latestPrice) {
        this.sector = sector;
        this.latestPrice = latestPrice;
    }
}
