package com.ecommerce.hamroDaraz.ServiceImpl;


import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import com.ecommerce.hamroDaraz.Repository.UserRepo;
import com.ecommerce.hamroDaraz.Repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeleteOTP {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private UserRepo userRepository;

    @Scheduled(cron = "0 */1 * * * ?")
    public void cleanupExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<VerificationToken> expiredTokens = verificationTokenRepository.findByExpiryDateBefore(now);

        for (VerificationToken token : expiredTokens) {
            verificationTokenRepository.delete(token);
            User user = token.getUser();
            userRepository.delete(user);}
}
}
