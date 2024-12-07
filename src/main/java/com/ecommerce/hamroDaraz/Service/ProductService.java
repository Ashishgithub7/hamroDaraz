package com.ecommerce.hamroDaraz.Service;

import com.ecommerce.hamroDaraz.DTO.ProductDTO;
import com.ecommerce.hamroDaraz.Entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ResponseEntity<?> addProduct(ProductDTO productDto);

    ResponseEntity<?> deleteProduct(Long shopId, String productName);
}
