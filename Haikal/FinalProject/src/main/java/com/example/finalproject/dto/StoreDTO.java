package com.example.finalproject.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private Integer user_id;
    private String storeName;
    private String storeNumberOwner;

    private String city;
    private String district;

    private String street;
    private String phoneNumber;

    private String email;

    private String companyRegisterUrl;
    private boolean isActive; // by admin
    private String commercialLicense;

}
