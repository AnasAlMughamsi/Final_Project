package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.AuthService;
import com.example.finalproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class AuthController {

    // This sing-up/login controller for customer & store owner
    private final AuthService authService;
    private final CustomerService customerService;

//    @PostMapping("/customer")
//    public ResponseEntity<ApiResponse> customerRegister(@Valid @RequestBody CustomerDTO customerDTO) {
//        authService.RegisterCustomer(customerDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("customer registered!", 201));
//    }

    @PostMapping("/customer")
    public ResponseEntity<ApiResponse> customerRegister(@RequestBody MyUser myUser) {
        authService.registerCustomer(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered!", 201));
    }

//    @PostMapping("/store")
//    public ResponseEntity<ApiResponse> storeRegister(@Valid @RequestBody StoreDTO storeDTO) {
//        authService.RegisterStore(storeDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("store registered!", 201));
//    }


    @PostMapping("/store")
    public ResponseEntity<ApiResponse> storeLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Store logged in successfully!", 201));
    }
}
