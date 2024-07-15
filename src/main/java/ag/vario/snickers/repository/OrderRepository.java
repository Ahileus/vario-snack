package ag.vario.snickers.repository;

import ag.vario.snickers.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
