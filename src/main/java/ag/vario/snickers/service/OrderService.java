package ag.vario.snickers.service;

import ag.vario.snickers.dto.OrderDTO;

public interface OrderService {
    OrderDTO getOrderDTOById(Long id);
}
