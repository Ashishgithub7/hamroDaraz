package com.ecommerce.hamroDaraz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;

   private String username;

   private String password;

   private String firstName;

   private String lastName;

   private String email;

   private String phoneNumber;

   private String role;

   private Boolean enabled;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime createdDateTime;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime modifiedDateTime;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
   @JsonIgnore
   private Cart cart;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
   @JsonIgnore
   private VerificationToken verificationToken;

   @OneToOne(mappedBy = "user-id", cascade = CascadeType.ALL,orphanRemoval = true)
   @JsonIgnore
   private Shop shop;
}
