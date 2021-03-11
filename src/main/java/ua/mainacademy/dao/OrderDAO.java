package ua.mainacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mainacademy.model.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
}
