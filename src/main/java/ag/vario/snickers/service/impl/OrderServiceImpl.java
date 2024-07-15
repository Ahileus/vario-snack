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
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.mapToOrderDTO(order);
    }

//    public OrderDTO addOrderPosition(Long orderId, OrderPositionDTO positionDTO) {
//        ProductPosition position = mapper.mapPositionDtoToPosition(positionDTO);
//
//        Optional<Order> byId = orderRepository.findById(orderId);
//        Order order = byId.orElseThrow();
//        order.getOrderpositions().add(position);
//
//        orderRepository.save(order);
//    }
}
