package interview.dto;

import lombok.Data;

@Data
public class AllocationsDto {
    private String sector;
    private double assetValue;
    private double proportion;

    public AllocationsDto(String sector, double assetValue, double proportion) {
        this.sector = sector;
        this.assetValue = assetValue;
        this.proportion = proportion;
    }

}