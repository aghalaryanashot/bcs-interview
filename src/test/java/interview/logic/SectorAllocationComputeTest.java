package interview.logic;

import interview.dto.*;
import interview.model.StockPriceValue;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SectorAllocationComputeTest {
    @Test
    public void testLogic() {
        AllocationsDto obj1;
        AllocationsDto obj2;
        Map<String, Double> prices =  new HashMap<>();
        prices.put("Technology",2.0);
        prices.put("Cars",200.0);
        StockPriceValue stockPriceValue = new StockPriceValue(202,prices);
        ValueAndAllocationsDto valueAndAllocationsDto = SectorAllocationCompute.finalRated(stockPriceValue);
        obj1=valueAndAllocationsDto.getAllocations().get(0);
        obj2=valueAndAllocationsDto.getAllocations().get(1);
        assertEquals(obj1.getSector(),"Cars");
        assertEquals(obj2.getSector(),"Technology");
        assertEquals(obj1.getAssetValue(),200.0,0.1);
        assertEquals(obj2.getAssetValue(),2.0,0.1);
        assertEquals(obj1.getProportion(),0.9900,0.0001);
        assertEquals(obj2.getProportion(),0.0099,0.0001);
    }
}