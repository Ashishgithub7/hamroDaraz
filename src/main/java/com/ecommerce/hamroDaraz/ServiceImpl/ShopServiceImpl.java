package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.Config.JwtTokenHelper;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Repository.ShopRepo;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ShopRepo shopRepository;
 @Override
    public ShopRegisterResponse registerShop(ShopRegisterRequest request,String userToken) {
     String token = userToken.replaceAll("Bearer", " ").trim();
     Long userId = jwtTokenHelper.extractUserIdFromToken(token);
     System.out.println("UserId found: "+userId);
     User user = userRepository.findById(userId)
             .orElseThrow(() -> new RuntimeException("User Not Found"));

     Shop shop = modelMapper.map(request, Shop.class);
     shop.setCreatedAt(LocalDateTime.now());
     shop.setUser(user);
     Shop savedShop = shopRepository.save(shop);
     ShopRegisterResponse response = modelMapper.map(savedShop, ShopRegisterResponse.class);
     return response;
 }
}
