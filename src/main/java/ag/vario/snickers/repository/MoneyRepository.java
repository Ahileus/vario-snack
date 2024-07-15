package ag.vario.snickers.repository;

import ag.vario.snickers.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Long> {
}
