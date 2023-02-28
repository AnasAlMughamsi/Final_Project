package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Store;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.service.MyUserDetailsService;
import com.example.finalproject.service.MyUserService;
import com.example.finalproject.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    // this login controller for admin
    private final MyUserService myUserService;
    private final CustomerService customerService;
    private final StoreService storeService;



    @GetMapping("/all-users")
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> userList = myUserService.getAllMyUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @GetMapping("/all-customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = myUserService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/all-store")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> store = storeService.getAllStores();
        return ResponseEntity.status(200).body(store);
    }
}
