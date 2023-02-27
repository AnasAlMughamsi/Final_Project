package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.model.Customer;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.service.MyUserDetailsService;
import com.example.finalproject.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ap1/v1/admin")
public class AdminController {

    // this login controller for admin
    private final MyUserService myUserService;
    private final CustomerService customerService;
    @GetMapping("/all-customer")
    public ResponseEntity<List<Customer>> getAllUsers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }
}
