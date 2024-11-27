package com.ecommerce.hamroDaraz.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @PostMapping("/registerShop")
    public String registerShop() {

    }
}
