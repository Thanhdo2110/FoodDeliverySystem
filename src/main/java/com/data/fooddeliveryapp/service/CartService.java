package com.data.fooddeliveryapp.service;

import com.data.fooddeliveryapp.io.CartRequest;
import com.data.fooddeliveryapp.io.CartResponse;

public interface CartService {

     CartResponse addToCart(CartRequest request);
}
