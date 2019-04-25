package interview.logic;

import interview.dto.*;
import interview.model.StockPriceValue;

import java.util.*;

public class SectorAllocationCompute {

    public static ValueAndAllocationsDto finalRated(StockPriceValue stockPriceValue) {
        ArrayList<AllocationsDto> allocationsList = new ArrayList<>();
        for (Map.Entry<String,Double> obj:stockPriceValue.getMap().entrySet()) {
            String sector = obj.getKey();
            double price = obj.getValue();
            double proportion = obj.getValue() / stockPriceValue.getValue();
            allocationsList.add(new AllocationsDto(sector, price, proportion));
        }
        return new ValueAndAllocationsDto(stockPriceValue.getValue(), allocationsList);
    }
}
