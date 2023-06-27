package com.grupo3a.ecommercefrutos2.service;

import com.grupo3a.ecommercefrutos2.entity.User;
import com.grupo3a.ecommercefrutos2.repository.OrderItemRepository;
import com.grupo3a.ecommercefrutos2.entity.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> getOrdersOfUser(User user) {
        List<OrderItem> orders = repository.findByUser(user);
        return orders == null ? new ArrayList<>(): orders;
    }

    public void addOrdersOfUser(List<OrderItem> orders) {
        repository.saveAll(orders);
    }
}
