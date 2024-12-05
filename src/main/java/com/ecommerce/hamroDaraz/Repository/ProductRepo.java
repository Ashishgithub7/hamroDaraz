package com.ecommerce.hamroDaraz.Repository;

import com.ecommerce.hamroDaraz.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
