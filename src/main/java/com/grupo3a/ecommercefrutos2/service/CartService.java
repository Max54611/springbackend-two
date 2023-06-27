package com.grupo3a.ecommercefrutos2.service;

import com.grupo3a.ecommercefrutos2.entity.Cart;
import com.grupo3a.ecommercefrutos2.entity.CartItem;
import com.grupo3a.ecommercefrutos2.entity.User;
import com.grupo3a.ecommercefrutos2.entity.embeddable.Product;
import com.grupo3a.ecommercefrutos2.exception.FailedRequestException;
import com.grupo3a.ecommercefrutos2.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    @Autowired
    private CartRepository repository;
    @Autowired
    private FetchService fetchService;

    private Cart initializeCart(Cart cart) {
        if (cart == null) throw new RuntimeException("Cannot retrieve cart");
        if (cart.getCartItems() == null) cart.setCartItems(new ArrayList<>());
        List<CartItem> cartItems = cart.getCartItems();
        List<Integer> ids = cartItems.stream()
                .mapToInt((item) -> item.getProduct().getProductId())
                .boxed()
                .toList();
        List<Product> products = fetchService.getProductsById(ids);

        int total = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            Product product = products.get(i);
            CartItem cartItem = cartItems.get(i);
            if (product.getProductId().intValue() != cartItem.getProduct().getProductId().intValue())
                throw new FailedRequestException("Invalid data from server");
            cartItem.setProduct(product);
            total += cartItem.calculateAmount();
        }
        cart.setTotalAmount(total);
        return cart;
    }

    public Cart getCartByUser(User user) {
        Cart cart = repository.findByUser(user).orElse(null);
        return initializeCart(cart);
    }

    public Cart addCartOfUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return repository.save(cart);
    }
}

