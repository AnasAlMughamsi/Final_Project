package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class AuthController {

    // This sing-up/login controller for customer & store owner
    private final AuthService authService;
    @PostMapping("/customer")
    public ResponseEntity<ApiResponse> customerRegister(@Valid @RequestBody CustomerDTO customerDTO) {
        authService.RegisterCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("customer registered!", 201));
    }
//    @PostMapping("/store")
//    public ResponseEntity<ApiResponse> storeRegister(@Valid @RequestBody StoreDTO storeDTO) {
//        authService.RegisterStore(storeDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("store registered!", 201));
//    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> customerLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer logged in successfully", 200));
    }

    @PostMapping("/store")
    public ResponseEntity<ApiResponse> storeLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Store logged in successfully!", 201));
    }
}
