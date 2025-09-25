package com.data.fooddeliveryapp.controller;


import com.data.fooddeliveryapp.io.FoodRequest;
import com.data.fooddeliveryapp.io.FoodResponse;
import com.data.fooddeliveryapp.service.FoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@RequestPart("food")String foodString,
                                @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        FoodRequest request = null;
        try {
            request=objectMapper.readValue(foodString,FoodRequest.class);
        }catch (JsonProcessingException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid JSON format");
        }
        FoodResponse response =foodService.addFood(request,file);
        return response;
    }
}
