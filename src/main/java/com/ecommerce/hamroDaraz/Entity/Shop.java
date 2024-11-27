package com.ecommerce.hamroDaraz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {

    @Id
    private Long id;

    private String name;

    private String contactInfo;

    private String logoUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime updatedAt;

    private String description;

    @OneToOne
    @JoinColumn(name = "user-id")
    private User user;

}
