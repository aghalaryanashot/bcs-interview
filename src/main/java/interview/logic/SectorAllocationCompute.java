package interview.logic;

import interview.dto.*;
import interview.model.StockPriceValue;

import java.util.*;

public class SectorAllocationCompute {

    public static ValueAndAllocationsDto finalRated(StockPriceValue stockPriceValue) {
        ArrayList<AllocationsDto> allocationsList = new ArrayList<>();
        for (IexStockPriceDto obj:stockPriceValue.getListStockPrice()) {
            String sector = obj.getSector();
            double price = obj.getLatestPrice();
            double proportion = obj.getLatestPrice() / stockPriceValue.getValue();
            allocationsList.add(new AllocationsDto(sector, price, proportion));
        }
        return new ValueAndAllocationsDto(stockPriceValue.getValue(), allocationsList);
    }
}
