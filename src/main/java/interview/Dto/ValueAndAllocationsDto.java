package interview.Dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ValueAndAllocationsDto {
    private double value;
    private ArrayList<AllocationsDto> allocations;

    public ValueAndAllocationsDto(double value, ArrayList<AllocationsDto> allocationsList) {
        this.value = value;
        this.allocations = allocationsList;
    }
}
