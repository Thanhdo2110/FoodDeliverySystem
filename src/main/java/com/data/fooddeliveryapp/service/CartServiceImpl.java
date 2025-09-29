package com.data.fooddeliveryapp.service;


import com.data.fooddeliveryapp.entity.CartEntity;
import com.data.fooddeliveryapp.io.CartRequest;
import com.data.fooddeliveryapp.io.CartResponse;
import com.data.fooddeliveryapp.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    public CartResponse addToCart(CartRequest request) {
         String loggedInUserId = userService.findByUserId();
         Optional<CartEntity> cartOptinal = cartRepository.findByUserId(loggedInUserId);
         CartEntity cart = cartOptinal.orElseGet(()-> new CartEntity(loggedInUserId, new HashMap<>()));
          Map<String,Integer> cartItems= cart.getItems();
          cartItems.put(request.getFoodId(),cartItems.getOrDefault(request.getFoodId(),0)+1);
          cart.setItems(cartItems);
          cart = cartRepository.save(cart);
           return converToResponse(cart);
         }
         private  CartResponse converToResponse(CartEntity cartEntity){
         return CartResponse.builder()
                    .id(cartEntity.getId())
                    .userId(cartEntity.getUserId())
                    .items(cartEntity.getItems())
                    .build();
         }
}
