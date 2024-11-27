package com.ecommerce.hamroDaraz.Service;

import com.ecommerce.hamroDaraz.DTO.UserDTO;
import com.ecommerce.hamroDaraz.DTO.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    ResponseEntity<?> userLogin(LoginRequest request);
}
