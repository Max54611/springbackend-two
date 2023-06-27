package com.grupo3a.ecommercefrutos2.repository;

import com.grupo3a.ecommercefrutos2.entity.Cart;
import com.grupo3a.ecommercefrutos2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
}
