package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.CustomExceptionHandler.ResourceAlreadyExistsException;
import com.ecommerce.hamroDaraz.CustomExceptionHandler.ResourceNotFoundException;
import com.ecommerce.hamroDaraz.DTO.ProductDTO;
import com.ecommerce.hamroDaraz.Entity.Product;
import com.ecommerce.hamroDaraz.Entity.Shop;
import com.ecommerce.hamroDaraz.Repository.ProductRepo;
import com.ecommerce.hamroDaraz.Repository.ShopRepo;
import com.ecommerce.hamroDaraz.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> addProduct(ProductDTO productDto) {
        Long shopId = productDto.getShopId();
        Shop shop = shopRepo.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop","ShopID",shopId));
        List<Product> shopProducts = shop.getProducts();
        if(productRepo.findByNameAndShopId(productDto.getProductName(), shopId)!=null){
           throw new ResourceAlreadyExistsException("Product","ProductName",productDto.getProductName());
        }
        Product newProduct = modelMapper.map(productDto, Product.class);
        newProduct.setCreatedAt(LocalDateTime.now());
        shopProducts.add(newProduct);
        shop.setProducts(shopProducts);
        Product savedProduct = productRepo.save(newProduct);
        shopRepo.save(shop);
        return ResponseEntity.ok(modelMapper.map(savedProduct, ProductDTO.class));
    }
}
