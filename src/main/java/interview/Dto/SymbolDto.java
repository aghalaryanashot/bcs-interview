package interview.Dto;

import lombok.Data;

@Data
public class SymbolDto {
    private String symbol;
    private int volume;

    public SymbolDto(String symbol, int volume) {
        this.symbol = symbol;
        this.volume = volume;
    }
}
