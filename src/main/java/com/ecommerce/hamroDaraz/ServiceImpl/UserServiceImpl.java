package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.DTO.UserDTO;
import com.ecommerce.hamroDaraz.Entity.Cart;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import com.ecommerce.hamroDaraz.Repository.CartRepo;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private VerificationTokenServiceImpl verificationTokenService;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    user.setCreatedDateTime(LocalDateTime.now());
    user.setRole("Buyer");

    Cart myCart= new Cart();  // create new cart while registering new user
    myCart.setTotalPrice(0L);

    User newUser = userRepo.save(user);  //save in db
    myCart.setUser(newUser); //set user by user-id in the cart
    cartRepo.save(myCart);

        String otp = String.format("%06d", (int) (Math.random() * 1000000));
        verificationTokenService.createVerificationToken(user, otp);

        emailService.sendVerificationEmail(user.getEmail(), otp);

        return modelMapper.map(newUser, UserDTO.class);
    }
}
