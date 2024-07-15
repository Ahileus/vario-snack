package ag.vario.snickers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDTO {
    private Long id;
    private String name;
    private Long count;
    private String description;
    private BigDecimal worth;
}
