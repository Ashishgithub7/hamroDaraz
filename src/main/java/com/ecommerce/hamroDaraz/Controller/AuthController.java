package com.ecommerce.hamroDaraz.Controller;

import com.ecommerce.hamroDaraz.DTO.AdminDTO;
import com.ecommerce.hamroDaraz.DTO.UserDTO;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.MessageConstant.ErrorMessageConstant;
import com.ecommerce.hamroDaraz.Repository.AdminRepo;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Service.AdminService;
import com.ecommerce.hamroDaraz.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hamroDaraz/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private static final Logger logInfo = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

        System.out.println("register API hit!!");

        List<Map<String,String>> errors = new ArrayList<>();

        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("usernameError", ErrorMessageConstant.USERNAME_ERROR);
            errors.add(error);
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("passwordError", ErrorMessageConstant.PASSWORD_ERROR);
            errors.add(error);
        }
        if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("emailError", ErrorMessageConstant.EMAIL_ERROR);
            errors.add(error);
        }
        if(userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("phoneNumberError",ErrorMessageConstant.PHONE_ERROR);
            errors.add(error);
        }
        if(userDTO.getConfirmPassword() == null || userDTO.getConfirmPassword().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("confirmPasswordError",ErrorMessageConstant.CONFIRM_PASSWORD_ERROR);
            errors.add(error);
        }

        if(userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("firstNameError",ErrorMessageConstant.FIRST_NAME_ERROR);
            errors.add(error);
        }
        if(userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("lastNameError",ErrorMessageConstant.LAST_NAME_ERROR);
            errors.add(error);
        }

//        Empty validation Finishes

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            Map<String,String> error = new HashMap<>();
            error.put("passwordError",ErrorMessageConstant.PASSWORD_DOES_NOT_MATCH_ERROR);
            errors.add(error);
        }

        if (!errors.isEmpty()) {
            logInfo.error(errors.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", errors));
        }

       if (userRepo.existsByPhoneNumber(userDTO.getPhoneNumber())) {
           logInfo.error("User with same phone number already exists");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(Map.of("status", "error", "message", ErrorMessageConstant.PHONE_NUMBER_ALREADY_EXISTS));
       }
       if(userRepo.existsByEmail(userDTO.getEmail())){
           logInfo.error("User with same email already exists");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(Map.of("status", "error", "message", ErrorMessageConstant.EMAIL_ALREADY_EXISTS));
       }

        return ResponseEntity.ok(userService.registerUser(userDTO));

    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminDTO adminDTO) {

        List<Map<String,String>> errors = new ArrayList<>();

        if (adminDTO.getUsername() == null || adminDTO.getUsername().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("usernameError", ErrorMessageConstant.USERNAME_ERROR);
            errors.add(error);
        }
        if (adminDTO.getPassword() == null || adminDTO.getPassword().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("passwordError", ErrorMessageConstant.PASSWORD_ERROR);
            errors.add(error);
        }
        if(adminDTO.getEmail() == null || adminDTO.getEmail().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("emailError", ErrorMessageConstant.EMAIL_ERROR);
            errors.add(error);
        }
        if(adminDTO.getConfirmPassword() == null || adminDTO.getConfirmPassword().isEmpty()) {
            Map<String,String> error = new HashMap<>();
            error.put("confirmPasswordError",ErrorMessageConstant.CONFIRM_PASSWORD_ERROR);
            errors.add(error);
        }

        if (!adminDTO.getPassword().equals(adminDTO.getConfirmPassword())) {
            Map<String,String> error = new HashMap<>();
            error.put("passwordError",ErrorMessageConstant.PASSWORD_DOES_NOT_MATCH_ERROR);
            errors.add(error);
        }

        if (!errors.isEmpty()) {
            logInfo.error(errors.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", errors));
        }

        if(userRepo.existsByEmail(adminDTO.getEmail())){
            logInfo.error("User with same email already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", ErrorMessageConstant.EMAIL_ALREADY_EXISTS));
        }

        return ResponseEntity.ok(adminService.registerAdmin(adminDTO));
    }
}
