package com.ecommerce.hamroDaraz.Service;

import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ShopService {
    ShopRegisterResponse registerShop(ShopRegisterRequest request, String token);
}
