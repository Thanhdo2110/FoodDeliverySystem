package com.data.fooddeliveryapp.service;

import com.data.fooddeliveryapp.io.FoodRequest;
import com.data.fooddeliveryapp.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {

    String uploadFile(MultipartFile file);

     FoodResponse addFood(FoodRequest request,MultipartFile file);

     List<FoodResponse> readFood();

     FoodResponse readFoodById(String id);

      boolean deleteFile(String filename);

      void deleteFoodById(String id);
}
