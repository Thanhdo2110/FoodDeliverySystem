package com.data.fooddeliveryapp.service;

import com.data.fooddeliveryapp.io.FoodRequest;
import com.data.fooddeliveryapp.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {

    String uploadFile(MultipartFile file);

     FoodResponse addFood(FoodRequest request,MultipartFile file);
}
