package ag.vario.snickers.service;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.OrderPreisDTO;
import ag.vario.snickers.dto.ProductPositionDTO;
import ag.vario.snickers.model.MoneyPosition;
import ag.vario.snickers.model.Order;
import ag.vario.snickers.model.ProductPosition;

import java.math.BigDecimal;
import java.util.Set;

public interface OrderService {
    OrderDTO getOrderDTOById(Long id);
    Order getOrderById(Long orderId);
    Set<ProductPosition> getProductPosition(Order order, Set<ProductPositionDTO> positionDTO);
    Set<MoneyPosition> getMoneyPosition(Order order, Set<MoneyPositionDTO> positionDTO);
    Order saveOrder(Order order);
    OrderPreisDTO getPreisForOrder(Order order);
    BigDecimal getMoneyForOrder(Order order);

}
