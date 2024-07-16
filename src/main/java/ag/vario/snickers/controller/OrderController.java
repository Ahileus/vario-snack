package ag.vario.snickers.controller;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.model.MoneyPosition;
import ag.vario.snickers.model.Order;
import ag.vario.snickers.model.ProductPosition;
import ag.vario.snickers.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {

        Order order = new Order();
        if (orderDTO != null && orderDTO.getId() != null) {
            order = orderService.getOrderById(orderDTO.getId());
        } else {
            order = orderService.saveOrder(order);
        }

        if (orderDTO.getMoneyPositions().size() > 0) {
            Set<MoneyPosition> moneyPosition = orderService.getMoneyPosition(orderDTO.getMoneyPositions());
            order.getMoneypositions().addAll(moneyPosition);
        }

        if (orderDTO.getProductPositions().size() > 0) {
            Set<ProductPosition> productPosition = orderService.getProductPosition(orderDTO.getProductPositions());
            order.getProductpositions().addAll(productPosition);
        }

        order = orderService.saveOrder(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
