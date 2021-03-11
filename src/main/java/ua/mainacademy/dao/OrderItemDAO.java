package ua.mainacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mainacademy.model.OrderItem;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
}
