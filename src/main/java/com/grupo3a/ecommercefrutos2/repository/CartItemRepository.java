package com.grupo3a.ecommercefrutos2.repository;

import com.grupo3a.ecommercefrutos2.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}