package com.ecommerce.hamroDaraz.Repository;

import com.ecommerce.hamroDaraz.DTO.ShopRegisterResponse;
import com.ecommerce.hamroDaraz.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {
    Shop findByUserId(long userId);
}
