package com.ecommerce.hamroDaraz.Controller;

import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Service.ShopService;
import jakarta.validation.Valid;
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
                                          @Valid @RequestBody ShopRegisterRequest request) {
        System.out.println("shop creating api hit");
           return ResponseEntity.ok(shopService.registerShop(request,token));
    }

    @GetMapping("/getDetails/{userId}")
    public ResponseEntity<?> getDetails(@PathVariable Long userId) {
        System.out.println("shop get details api hit");
        return ResponseEntity.ok(shopService.getDetails(userId));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateShop(@RequestHeader(value = "Authorization")String token,
                                        @RequestBody ShopRegisterRequest request) {
        System.out.println("shop update api hit");
        return ResponseEntity.ok(shopService.update(token,request));
    }

    @DeleteMapping("/delete")
    public String deleteShop(@RequestHeader(value = "Authorization")String token){
        System.out.println("shop delete api hit");
        return shopService.deleteShop(token);
    }
}
