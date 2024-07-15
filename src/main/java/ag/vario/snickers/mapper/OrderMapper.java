package ag.vario.snickers.mapper;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.ProductPositionDTO;
import ag.vario.snickers.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO mapToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setMoneyPositions(order.getMoneypositions().stream().map(moneyposition -> {
            MoneyPositionDTO moneyPositionDTO = new MoneyPositionDTO();
            moneyPositionDTO.setId(moneyposition.getId());
            moneyPositionDTO.setMoneyId(moneyposition.getMoney().getId());
            moneyPositionDTO.setCount(moneyposition.getCount());
            return moneyPositionDTO;
        }).collect(Collectors.toSet()));

        orderDTO.setOrderPositions(order.getOrderpositions().stream().map(orderposition -> {
            ProductPositionDTO orderPositionDTO = new ProductPositionDTO();
            orderPositionDTO.setId(orderposition.getId());
            orderPositionDTO.setProductId(orderposition.getProduct().getId());
            orderPositionDTO.setCount(orderposition.getCount());
            return orderPositionDTO;
        }).collect(Collectors.toSet()));

        return orderDTO;
    }
}
