package com.ecommerce.hamroDaraz.Repository;

import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String otp);

    List<VerificationToken> findByExpiryDateBefore(LocalDateTime now);

    void deleteById(Long aLong);
}
