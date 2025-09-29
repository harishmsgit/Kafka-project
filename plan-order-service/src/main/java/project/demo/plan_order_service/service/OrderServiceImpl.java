package project.demo.plan_order_service.service;

import org.springframework.stereotype.Service;
import project.demo.plan_order_service.event.OrderEvent;
import project.demo.plan_order_service.kafka.OrderProducer;
import project.demo.plan_order_service.model.Order;
import project.demo.plan_order_service.model.dto.OrderDTO;
import project.demo.plan_order_service.repository.OrderRepository;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @Override
    public Order placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setName(orderDTO.getName());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order is creating......");
        orderEvent.setOrder(order);
        orderRepository.save(order);
        orderProducer.sendOrder(orderEvent);
        return order;
    }
}
