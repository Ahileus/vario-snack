package ag.vario.snickers.service;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.ProductPositionDTO;
import ag.vario.snickers.model.MoneyPosition;
import ag.vario.snickers.model.Order;
import ag.vario.snickers.model.ProductPosition;

import java.util.Set;

public interface OrderService {
    OrderDTO getOrderDTOById(Long id);
    Order getOrderById(Long orderId);
    Set<ProductPosition> getProductPosition(Set<ProductPositionDTO> positionDTO);
    Set<MoneyPosition> getMoneyPosition(Set<MoneyPositionDTO> positionDTO);
    Order saveOrder(Order order);

}
