package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Repository.VerificationTokenRepository;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationTokenServiceImpl {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserRepo userRepository;

    private static final int OTP_EXPIRATION_MINUTES = 5;

    public VerificationToken createVerificationToken(User user, String otp) {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setToken(otp);
        token.setCreatedDate(LocalDateTime.now());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        return verificationTokenRepository.save(token);
    }

    public VerificationToken getToken(String otp) {
        VerificationToken token = verificationTokenRepository.findByToken(otp);
        if (token != null && isTokenExpired(token)) {
            deleteToken(token);
            return null;
        }
        return token;
    }

    boolean isTokenExpired(VerificationToken token) {
        return LocalDateTime.now().isAfter(token.getCreatedDate().plusMinutes(OTP_EXPIRATION_MINUTES));
    }

    @Transactional
    public void deleteToken(VerificationToken token) {
        System.out.println(token);
//        verificationTokenRepository.delete(token);
        verificationTokenRepository.deleteById(token.getId());
        System.out.println("deleted verification token");
    }
}
