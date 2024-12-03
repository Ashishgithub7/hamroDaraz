package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.Config.JwtTokenHelper;
import com.ecommerce.hamroDaraz.CustomException.ResourceNotFoundException;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterRequest;
import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Repository.ShopRepo;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Service.ShopService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> getDetails(Long userId) {

        try{
        Shop response = shopRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Shop","UserID",userId));
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Shop",response));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message",e.getMessage()));
        }

    }


    @Override
    public ShopRegisterResponse update(String token, ShopRegisterRequest request) {
       Long userId = findUserIdFromToken(token);
       Shop updatedShop = shopRepository.findByUserId(userId)
               .orElseThrow(()-> new ResourceNotFoundException("Shop","UserID",userId));
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


    @Override
    @Transactional
    public ResponseEntity<?> deleteShop(String token) {
        Long userId = findUserIdFromToken(token);
        Shop shop = shopRepository.findByUserId(userId)
                        .orElseThrow(()-> new ResourceNotFoundException("Shop","UserID",userId));
        shopRepository.delete(shop);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message","Shop deleted successfully","Shop",shop));

    }

   private Long findUserIdFromToken(String token) {
        String trimmedToken = token.replaceAll("Bearer", " ").trim();
        Long userId = jwtTokenHelper.extractUserIdFromToken(trimmedToken);
        System.out.println("UserId found: "+userId);
        return userId;
    }
}

