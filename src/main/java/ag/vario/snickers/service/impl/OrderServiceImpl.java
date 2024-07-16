package ag.vario.snickers.service.impl;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.ProductPositionDTO;
import ag.vario.snickers.mapper.OrderMapper;
import ag.vario.snickers.model.*;
import ag.vario.snickers.repository.MoneyRepository;
import ag.vario.snickers.repository.OrderRepository;
import ag.vario.snickers.repository.ProductRepository;
import ag.vario.snickers.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MoneyRepository moneyRepository;

    @Override
    public OrderDTO getOrderDTOById(Long orderId) {
        Order order = this.getOrderById(orderId);
        return OrderMapper.mapToOrderDTO(order);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

//    public OrderDTO addOrderPosition(Long orderId, ProductPositionDTO positionDTO) {
//        Order order = this.getOrderById(orderId);
//        Product product = productRepository
//                .findById(positionDTO.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        ProductPosition position = OrderMapper.mapToProductPosition(positionDTO, order, product);
//
//        order.getProductpositions().add(position);
//
//        Order savedOrder = orderRepository.save(order);
//        return OrderMapper.mapToOrderDTO(savedOrder);
//    }

    @Override
    public Set<ProductPosition> getProductPosition(Set<ProductPositionDTO> positionDTO) {
        return positionDTO
                .stream()
                .map(position ->
                        {
                            Product product = productRepository
                                    .findById(position.getProductId())
                                    .orElseThrow(() -> new RuntimeException("Product not found"));
                            return OrderMapper.mapToProductPosition(position, product);
                        }
                )
                .collect(Collectors.toSet()) ;

    }

    @Override
    public Set<MoneyPosition> getMoneyPosition(Set<MoneyPositionDTO> positionDTO) {

        return positionDTO
                .stream()
                .map(position ->
                        {
                            Money money = moneyRepository
                                    .findById(position.getMoneyId())
                                    .orElseThrow(() -> new RuntimeException("Money not found"));
                            return OrderMapper.mapToMoneyPosition(position, money);
                        }
                )
                .collect(Collectors.toSet()) ;

    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
