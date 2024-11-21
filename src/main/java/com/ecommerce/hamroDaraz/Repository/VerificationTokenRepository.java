package com.ecommerce.hamroDaraz.Repository;

import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import com.ecommerce.hamroDaraz.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String otp);
}
