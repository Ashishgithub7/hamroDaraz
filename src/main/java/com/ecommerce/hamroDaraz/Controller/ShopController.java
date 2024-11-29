package com.ecommerce.hamroDaraz.Controller;

import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registerShop")
    public ResponseEntity<?> registerShop(ShopRegisterRequest request) {

    }
}
