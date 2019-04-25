package interview.logic;

import interview.dto.*;
import org.junit.Test;

import java.util.*;

public class SectorAllocationComputeTest {
    @Test
    public void testLogic() {

        StocksDto stocksDto = new StocksDto();
        List<SymbolDto> stocks = Arrays.asList(
                new SymbolDto("AAPL", 10),
                new SymbolDto("YNDX", 1000)
        );
        stocksDto.setStocks(stocks);
        Map<String, IexStockPriceDto> prices =  new HashMap<>();
        prices.put("AAPL",new IexStockPriceDto("Technology",2));
        prices.put("YNDX",new IexStockPriceDto("Technology",200));
        ValueAndAllocationsDto result = SectorAllocationCompute.compute(stocksDto, prices::get);
        double val = 200020;
        ArrayList<AllocationsDto> list = new ArrayList<>();
        list.add(new AllocationsDto("Technology",val,1));
        assertEquals(result,new ValueAndAllocationsDto(val,list));
    }
}