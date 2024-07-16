package ag.vario.snickers.service.impl;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.OrderPreisDTO;
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

import java.math.BigDecimal;
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

    @Override
    public OrderPreisDTO getPreisForOrder(Order order) {

        BigDecimal preis = order.getProductpositions()
                .stream()
                .map(product -> product.getProduct().getPreis().multiply(new BigDecimal(product.getCount())))
                .reduce(BigDecimal.ZERO, (product1, product2) -> product1.add(product2));

        return new OrderPreisDTO(order.getId(), preis);
    }

    @Override
    public BigDecimal getMoneyForOrder(Order order) {
        return order.getMoneypositions()
                .stream()
                .map(money -> money.getMoney().getWorth().multiply(new BigDecimal(money.getCount())))
                .reduce(BigDecimal.ZERO, (money1, money2) -> money1.add(money2));
    }

    @Override
    public Set<ProductPosition> getProductPosition(Order order, Set<ProductPositionDTO> positionDTO) {
        return positionDTO
                .stream()
                .map(position ->
                        {
                            Product product = productRepository
                                    .findById(position.getProductId())
                                    .orElseThrow(() -> new RuntimeException("Product not found"));
                            return OrderMapper.mapToProductPosition(position, order, product);
                        }
                )
                .collect(Collectors.toSet()) ;

    }

    @Override
    public Set<MoneyPosition> getMoneyPosition(Order order, Set<MoneyPositionDTO> positionDTO) {

        return positionDTO
                .stream()
                .map(position ->
                        {
                            Money money = moneyRepository
                                    .findById(position.getMoneyId())
                                    .orElseThrow(() -> new RuntimeException("Money not found"));
                            return OrderMapper.mapToMoneyPosition(position, order, money);
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
