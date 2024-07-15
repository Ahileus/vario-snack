package ag.vario.snickers.service;

import ag.vario.snickers.dto.MoneyDTO;

import java.util.List;

public interface MoneyService {
    MoneyDTO createMoney(MoneyDTO moneyDTO);
    MoneyDTO updateMoney(Long moneyId, MoneyDTO moneyDTO);
    MoneyDTO getMoneyById(Long moneyId);
    void deleteMoneyById(Long moneyId);
    List<MoneyDTO> getAllMoney();
}
