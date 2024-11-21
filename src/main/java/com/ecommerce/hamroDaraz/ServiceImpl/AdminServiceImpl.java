package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.DTO.AdminDTO;
import com.ecommerce.hamroDaraz.DTO.UserDTO;
import com.ecommerce.hamroDaraz.Entity.Admin;
import com.ecommerce.hamroDaraz.Entity.Cart;
import com.ecommerce.hamroDaraz.Entity.User;
import com.ecommerce.hamroDaraz.Repository.AdminRepo;
import com.ecommerce.hamroDaraz.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepo adminRepo;

    public AdminDTO registerAdmin(AdminDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        admin.setPassword(this.passwordEncoder.encode(admin.getPassword()));
        admin.setCreatedDateTime(LocalDateTime.now());
        admin.setRole("Admin");

        Admin newAdmin = adminRepo.save(admin);  //save in db

        return modelMapper.map(newAdmin, AdminDTO.class);
    }
}
