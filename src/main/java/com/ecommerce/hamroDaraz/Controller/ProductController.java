package com.ecommerce.hamroDaraz.Controller;

import com.ecommerce.hamroDaraz.CustomExceptionHandler.ResourceNotFoundException;
import com.ecommerce.hamroDaraz.DTO.ProductDTO;
import com.ecommerce.hamroDaraz.Entity.Product;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Repository.ShopRepo;
import com.ecommerce.hamroDaraz.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);

    }
}
