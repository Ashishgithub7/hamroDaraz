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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Override
    public ResponseEntity<?> addProduct(ProductDTO productDto) {
        Long shopId = productDto.getShopId();
        Shop shop = shopRepo.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Sh" +
                        "op","ShopID",shopId));
        List<Product> shopProducts = shop.getProducts();
        String normalizedProductName = productDto.getProductName().trim().toLowerCase();
        log.info("Normalized Product Name: {}", normalizedProductName);
        Optional<Product> existingProduct = productRepo.findByNameAndShopId(normalizedProductName, shopId);
        if (existingProduct.isPresent()) {
            throw new ResourceAlreadyExistsException("Product", "ProductName", productDto.getProductName());
        }
        Product newProduct = modelMapper.map(productDto, Product.class);
        newProduct.setCreatedAt(LocalDateTime.now());
        shopProducts.add(newProduct);
        shop.setProducts(shopProducts);
//        Product savedProduct = productRepo.save(newProduct);
        shopRepo.save(shop);
        return ResponseEntity.ok(modelMapper.map(newProduct, ProductDTO.class));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long ShopId, String productName) {
        Product product = productRepo.findByNameAndShopId(productName, ShopId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","ProductName",productName));
        productRepo.delete(product);
        return ResponseEntity.ok(Map.of("Successfully Deleted Product",modelMapper.map(product, ProductDTO.class)));
    }
}
