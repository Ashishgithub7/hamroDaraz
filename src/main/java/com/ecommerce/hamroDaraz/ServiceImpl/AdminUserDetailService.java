package com.ecommerce.hamroDaraz.ServiceImpl;

import com.ecommerce.hamroDaraz.Config.AdminUserDetail;
import com.ecommerce.hamroDaraz.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailService implements UserDetailsService {


    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for admin with email: " + username);

        return adminRepo.findByEmail(username)
                .map(AdminUserDetail::new)
                .orElseThrow(() -> {
                    System.out.println("Admin not found for email: " + username);
                    return new UsernameNotFoundException("No admin found");
                });    }
}
