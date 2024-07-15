package ag.vario.snickers.controller;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money")
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;

    @GetMapping
    public ResponseEntity<List<MoneyDTO>> getAllMoney() {
        List<MoneyDTO> money = moneyService.getAllMoney();
        return new ResponseEntity<>(money, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoneyDTO> getMoney(@PathVariable(name = "id") Long moneyId) {
        return new ResponseEntity<>(
                moneyService.getMoneyById(moneyId),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MoneyDTO> saveMoney(@RequestBody MoneyDTO moneyDTO) {
        MoneyDTO savedMoneyDTO = moneyService.createMoney(moneyDTO);
        return new ResponseEntity<>(savedMoneyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoneyDTO> updateMoney(@PathVariable(name = "id") Long moneyId,
                                                @RequestBody MoneyDTO moneyDTO) {
        MoneyDTO updatedMoneyDTO = moneyService.updateMoney(moneyId, moneyDTO);
        return new ResponseEntity<>(updatedMoneyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoney(@PathVariable(name = "id") Long moneyId) {
        moneyService.deleteMoneyById(moneyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
