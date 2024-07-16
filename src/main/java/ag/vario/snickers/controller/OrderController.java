package ag.vario.snickers.controller;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.OrderPreisDTO;
import ag.vario.snickers.model.MoneyPosition;
import ag.vario.snickers.model.Order;
import ag.vario.snickers.model.ProductPosition;
import ag.vario.snickers.service.MoneyService;
import ag.vario.snickers.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MoneyService moneyService;

    @PostMapping
    public ResponseEntity<OrderPreisDTO> createOrder(@RequestBody OrderDTO orderDTO) {

        Order order = new Order();
        if (orderDTO != null && orderDTO.getId() != null) {
            order = orderService.getOrderById(orderDTO.getId());
        } else {
            order = orderService.saveOrder(order);
        }


        Set<ProductPosition> productPosition = orderService.getProductPosition(order, orderDTO.getProductPositions());
        if (order.getProductpositions() != null) {
            order.getProductpositions().addAll(productPosition);
        } else {
            order.setProductpositions(productPosition);
        }

        order = orderService.saveOrder(order);

        OrderPreisDTO orderPreisDTO = orderService.getPreisForOrder(order);

        return new ResponseEntity<>(orderPreisDTO, HttpStatus.OK);
    }

    @PostMapping("/input")
    public ResponseEntity<OrderPreisDTO> inputMoney(@RequestBody OrderDTO orderDTO) {

        Order order = orderService.getOrderById(orderDTO.getId());

        Set<MoneyPosition> moneyPosition = orderService.getMoneyPosition(order, orderDTO.getMoneyPositions());
        if (order.getMoneypositions() != null) {
            order.getMoneypositions().addAll(moneyPosition);
        } else {
            order.setMoneypositions(moneyPosition);
        }

        BigDecimal preis = orderService.getPreisForOrder(order).getPreis();
        BigDecimal money = orderService.getMoneyForOrder(order);

        order = orderService.saveOrder(order);

        return new ResponseEntity<>(new OrderPreisDTO(order.getId(), money.subtract(preis)), HttpStatus.OK);
    }
}
