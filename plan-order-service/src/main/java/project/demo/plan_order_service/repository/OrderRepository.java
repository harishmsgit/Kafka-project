package project.demo.plan_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.plan_order_service.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    // Removed findByCategory(String category) as 'category' does not exist in Order entity
}
