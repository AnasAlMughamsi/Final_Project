package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {

    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;

}
