package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.Config.JwtTokenHelper;
import com.ecommerce.hamroDaraz.CustomExceptionHandler.ResourceNotFoundException;
import com.ecommerce.hamroDaraz.DTO.UserDTO;
import com.ecommerce.hamroDaraz.Entity.Cart;
import com.ecommerce.hamroDaraz.DTO.LoginRequest;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.DTO.UserLoginResponse;
import com.ecommerce.hamroDaraz.Repository.CartRepo;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    user.setCreatedDateTime(LocalDateTime.now());
    user.setRole("Buyer");
    user.setEnabled(false);  // make the account un-enabled at first

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

    @Override
    public ResponseEntity<?> userLogin(LoginRequest request) {

        User theUser = userRepo.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User","Email",request.getEmail()));
        UserLoginResponse response = new UserLoginResponse();
//        if(theUser.isPresent()){
//            User user = theUser.get();
//            System.out.println((user.getEmail()));
//
//            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
                );
                if (authentication.isAuthenticated()) {
                    response=modelMapper.map(theUser,UserLoginResponse.class);
                    response.setToken(jwtTokenHelper.generateToken(theUser.getEmail(), "user",theUser.getId()));
                    response.setMessage("Login successful");
                    response.setUserId(theUser.getId());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("status", "error", "message", "Invalid email or password. Please try again."));
//                }

//            }catch(AuthenticationException e){
//                return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Map.of("status", "error", "message", "Invalid email or password. Please try again."));
//            }
        }
//        else{
////            logInfo.error("User not found");
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(Map.of("status","error","message","user not found"));
//        }

//        EVERY LINE COMMENTED ABOVE IS ALTERNATE OF THE GLOBAL EXCEPTION HANDLING
    }
}
