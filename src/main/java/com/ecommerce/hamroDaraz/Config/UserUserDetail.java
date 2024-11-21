package com.ecommerce.hamroDaraz.Config;

import com.ecommerce.hamroDaraz.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserUserDetail implements UserDetails {
    private String username;
    private String password;

    private String role;
//    private Boolean isEnabled;

    public UserUserDetail(Object object){

    }
    public UserUserDetail(User user){
        this.username=user.getEmail();
        this.password=user.getPassword();
        this.role=user.getRole();
//        this.isEnabled=user.getEnabled();
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }
}
