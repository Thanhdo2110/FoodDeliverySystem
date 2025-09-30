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
         Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
         CartEntity cart = cartOptional.orElseGet(()-> new CartEntity(loggedInUserId, new HashMap<>()));
          Map<String,Integer> cartItems= cart.getItems();
          cartItems.put(request.getFoodId(),cartItems.getOrDefault(request.getFoodId(),0)+1);
          cart.setItems(cartItems);
          cart = cartRepository.save(cart);
           return convertToResponse(cart);
         }

    @Override
    public CartResponse getCart() {
        String loggedInUserId= userService.findByUserId();
        CartEntity entity = cartRepository.findByUserId(loggedInUserId)
                .orElse(new CartEntity(loggedInUserId,new HashMap<>()));
        return convertToResponse(entity);
    }

    @Override
    public void clearCart() {
        String loggedInUserId= userService.findByUserId();
        cartRepository.deleteByUserId(loggedInUserId);

    }

    @Override
    public CartResponse removeFromCart(CartRequest cartRequest){
        String loggedInUserId= userService.findByUserId();
        CartEntity entity = cartRepository.findByUserId(loggedInUserId)
                .orElseThrow(()-> new RuntimeException("Cart is not found"));
        Map<String ,Integer> cartItems=entity.getItems();
        String foodId = cartRequest.getFoodId();
        if (cartItems.containsKey(foodId)){
             int currentQty= cartItems.get(foodId);
             if (currentQty <= 1) {
                 cartItems.remove(foodId);
             } else {
                 cartItems.put(foodId, currentQty - 1);
             }
             entity.setItems(cartItems);
             entity = cartRepository.save(entity);
        }
        return convertToResponse(entity);
    }

    private  CartResponse convertToResponse(CartEntity cartEntity){
         return CartResponse.builder()
                    .id(cartEntity.getId())
                    .userId(cartEntity.getUserId())
                    .items(cartEntity.getItems())
                    .build();
         }
}
