package interview;

import interview.Dto.StocksDto;
import interview.Dto.ValueAndAllocationsDto;
import interview.logic.SectorAllocationCompute;
import org.springframework.web.bind.annotation.*;

@RestController
public class StocksController {

    @PostMapping("/")
    public ValueAndAllocationsDto allocations(
            @RequestBody StocksDto inputStocks
    ) {
        SectorAllocationCompute sectorAllocationCompute = new SectorAllocationCompute(RestBean.restTemplate());
        return sectorAllocationCompute.compute(inputStocks);
    }

}