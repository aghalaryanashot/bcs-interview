package interview.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StocksDto {
    private ArrayList<SymbolDto> stocks;
}
