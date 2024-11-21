package com.ecommerce.hamroDaraz.Service;

import com.ecommerce.hamroDaraz.DTO.AdminDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    AdminDTO registerAdmin(AdminDTO adminDTO);
}
