package ag.vario.snickers.service.impl;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.mapper.MoneyMapper;
import ag.vario.snickers.mapper.ProductMapper;
import ag.vario.snickers.model.Money;
import ag.vario.snickers.model.Product;
import ag.vario.snickers.repository.MoneyRepository;
import ag.vario.snickers.service.MoneyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MoneyServiceImpl implements MoneyService {

    private final MoneyRepository moneyRepository;

    @Override
    public MoneyDTO createMoney(MoneyDTO moneyDTO) {
        Money money = MoneyMapper.mapToMoney(moneyDTO);
        Money savedMoney = moneyRepository.save(money);
        return MoneyMapper.mapToMoneyDTO(savedMoney);
    }

    @Override
    public MoneyDTO updateMoney(Long moneyId, MoneyDTO moneyDTO) {
        Money foundMoney = this.findMoneyById(moneyId);
        Money money = MoneyMapper.merge(moneyDTO, foundMoney);
        Money savedMoney = moneyRepository.save(money);

        return MoneyMapper.mapToMoneyDTO(savedMoney);
    }

    @Override
    public MoneyDTO getMoneyById(Long moneyId) {
        Money money = this.findMoneyById(moneyId);

        return MoneyMapper.mapToMoneyDTO(money);
    }

    @Override
    public void deleteMoneyById(Long moneyId) {
        Money money = this.findMoneyById(moneyId);
        moneyRepository.delete(money);
    }

    @Override
    public List<MoneyDTO> getAllMoney() {
        List<Money> money = moneyRepository.findAll();
        return money.stream().map(MoneyMapper::mapToMoneyDTO).toList();
    }

    private Money findMoneyById(Long moneyId) {
        return moneyRepository
                .findById(moneyId)
                .orElseThrow(() -> new RuntimeException("Money not found"));
    }
}
