package com.grupo3a.ecommercefrutos2.repository;

import com.grupo3a.ecommercefrutos2.entity.User;
import com.grupo3a.ecommercefrutos2.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByUser(User user);
}
