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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IexStockPriceDto that = (IexStockPriceDto) o;
        return sector.equals(that.sector);
    }
}
