package project.demo.plan_order_service.service;

import project.demo.plan_order_service.model.Order;
import project.demo.plan_order_service.model.dto.OrderDTO;

public interface OrderService {
    Order placeOrder(OrderDTO orderDTO);
}
