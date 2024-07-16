package ag.vario.snickers.mapper;

import ag.vario.snickers.dto.MoneyPositionDTO;
import ag.vario.snickers.dto.OrderDTO;
import ag.vario.snickers.dto.ProductPositionDTO;
import ag.vario.snickers.model.*;

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

        orderDTO.setProductPositions(order.getProductpositions().stream().map(orderposition -> {
            ProductPositionDTO productPositionDTO = new ProductPositionDTO();
            productPositionDTO.setId(orderposition.getId());
            productPositionDTO.setProductId(orderposition.getProduct().getId());
            productPositionDTO.setCount(orderposition.getCount());
            return productPositionDTO;
        }).collect(Collectors.toSet()));

        return orderDTO;
    }

    public static MoneyPosition mapToMoneyPosition(MoneyPositionDTO moneyPositionDTO, Money money) {

        MoneyPosition moneyPosition = new MoneyPosition();
        moneyPosition.setCount(moneyPositionDTO.getCount());
        moneyPosition.setMoney(money);

        return moneyPosition;
    }

    public static ProductPosition mapToProductPosition(ProductPositionDTO productPositionDTO, Product product) {

        ProductPosition productPosition = new ProductPosition();
        productPosition.setCount(productPositionDTO.getCount());
        productPosition.setProduct(product);

        return productPosition;
    }
}
