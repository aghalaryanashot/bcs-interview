package interview.Dto;

import lombok.Data;
import java.util.List;

@Data
public class StocksDto {
    private List<SymbolDto> stocks;
}
