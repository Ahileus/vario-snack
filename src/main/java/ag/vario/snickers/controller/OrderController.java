package ag.vario.snickers.controller;

import ag.vario.snickers.dto.MoneyDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO order) {

        if (order == null && order.getId() == null) {
            new RuntimeException("Order Id is null!");
        }
        OrderDTO orderDTO = orderService.getOrderById(order.getId());
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
