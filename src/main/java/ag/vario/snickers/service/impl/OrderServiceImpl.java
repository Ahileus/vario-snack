package ag.vario.snickers.service.impl;

import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.mapper.OrderMapper;
import ag.vario.snickers.model.Order;
import ag.vario.snickers.model.ProductPosition;
import ag.vario.snickers.repository.OrderRepository;
import ag.vario.snickers.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO getOrderDTOById(Long orderId) {
        Order order = this.getOrderById(orderId);
        return OrderMapper.mapToOrderDTO(order);
    }

    public OrderDTO addOrderPosition(Long orderId, OrderPositionDTO positionDTO) {
        ProductPosition position = mapper.mapPositionDtoToPosition(positionDTO);

        Order order = this.getOrderById(orderId);
        order.getOrderpositions().add(position);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    private Order getOrderById(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
