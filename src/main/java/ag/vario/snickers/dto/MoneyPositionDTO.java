package ag.vario.snickers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPositionDTO {
    private Long id;
    private Long count;
    private Long moneyId;
}
