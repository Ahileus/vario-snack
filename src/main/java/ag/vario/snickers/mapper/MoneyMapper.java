package ag.vario.snickers.mapper;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.model.Money;

public class MoneyMapper {

    public static MoneyDTO mapToMoneyDTO(Money money) {
        MoneyDTO moneyDTO = new MoneyDTO();
        moneyDTO.setId(money.getId());
        moneyDTO.setName(money.getName());
        moneyDTO.setCount(money.getCount());
        moneyDTO.setDescription(money.getDescription());
        moneyDTO.setWorth(money.getWorth());
        return moneyDTO;
    }

    public static Money mapToMoney(MoneyDTO moneyDTO) {
        Money money = new Money();
        money.setName(moneyDTO.getName());
        money.setCount(moneyDTO.getCount());
        money.setDescription(moneyDTO.getDescription());
        money.setWorth(moneyDTO.getWorth());
        return money;
    }

    public static Money merge(MoneyDTO source, Money target) {
        target.setName(source.getName());
        target.setCount(source.getCount());
        target.setDescription(source.getDescription());
        target.setWorth(source.getWorth());
        return target;
    }
}
