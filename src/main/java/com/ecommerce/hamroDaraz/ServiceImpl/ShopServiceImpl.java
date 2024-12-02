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
    Long userId = findUserIdFromToken(userToken);
    User user = userRepository.findById(userId)
             .orElseThrow(() -> new RuntimeException("User Not Found"));

    Shop shop = modelMapper.map(request, Shop.class);
    shop.setCreatedAt(LocalDateTime.now());
    shop.setUser(user);
    Shop savedShop = shopRepository.save(shop);
    ShopRegisterResponse response = modelMapper.map(savedShop, ShopRegisterResponse.class);
    return response;
 }

    @Override
    public ShopRegisterResponse getDetails(Long userId) {
        Shop response = shopRepository.findByUserId(userId);
        return modelMapper.map(response, ShopRegisterResponse.class);
    }

    @Override
    public ShopRegisterResponse update(String token, ShopRegisterRequest request) {
       Long userId = findUserIdFromToken(token);
       Shop updatedShop = shopRepository.findByUserId(userId);
       if (request.getContactInfo()!=null) {
           updatedShop.setContactInfo(request.getContactInfo());
       }
       if(request.getDescription()!=null) {
           updatedShop.setDescription(request.getDescription());
       }
       if (request.getName()!=null) {
           updatedShop.setName(request.getName());
       }
       if(request.getLogoUrl()!=null) {
           updatedShop.setLogoUrl(request.getLogoUrl());
       }
       updatedShop.setUpdatedAt(LocalDateTime.now());

       shopRepository.save(updatedShop);
       return modelMapper.map(updatedShop, ShopRegisterResponse.class);

    }

//    @Override
//    public String deleteShop(String token) {
//        Long userId = findUserIdFromToken(token);
//        Shop shop = shopRepository.findByUserId(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Shop not found for User ID: " + userId));
//        shopRepository.delete(shop);
//        return ""
//    }

   private Long findUserIdFromToken(String token) {
        String trimmedToken = token.replaceAll("Bearer", " ").trim();
        Long userId = jwtTokenHelper.extractUserIdFromToken(trimmedToken);
//        System.out.println("UserId found: "+userId);
        return userId;
    }
}
