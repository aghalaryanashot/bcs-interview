package interview.controller;

import interview.dto.IexStockPriceDto;
import interview.dto.StocksDto;
import interview.dto.ValueAndAllocationsDto;
import interview.logic.SectorAllocationCompute;
import interview.logic.StockInfoDao;
import interview.model.StockPriceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class StocksController {
    @Autowired
    private StockInfoDao stockInfoDao;

    @PostMapping("/info")
    public ValueAndAllocationsDto allocations(@RequestBody StocksDto inputStocks) {

        StockPriceValue stockPrice = stockInfoDao.getStockPrice(inputStocks);
        return SectorAllocationCompute.finalRated(stockPrice);
    }

}