package com.ecommerce.hamroDaraz.Controller;

import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/registerShop")
    public ResponseEntity<?> registerShop(@RequestHeader(value ="Authorization") String token,
                                          @RequestBody ShopRegisterRequest request) {
        System.out.println("shop creating api hit");
           return ResponseEntity.ok(shopService.registerShop(request,token));
    }
}
