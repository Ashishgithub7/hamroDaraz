package com.ecommerce.hamroDaraz.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopRegisterRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Shop Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9&.\\-' ]{3,50}$", message = "\"Invalid shop name. The name must be 3 to 50 characters long and can only contain letters, numbers, spaces, and the following special characters: &, ., -, '.\"\n")
    private String name;

    @NotNull(message = "Shop's contact information is required")
    @NotBlank(message = "Enter some contact information")
    @Size(min = 5, max = 200, message="Contact Info's length should be between 5 and 200")
    private String contactInfo;

    private String logoUrl;

    @NotNull(message = "Shop's contact information is required")
    @NotBlank(message = "Enter some contact information")
    @Size(min = 5, max = 200, message="Contact Info's length should be between 5 and 200")
    private String description;}
