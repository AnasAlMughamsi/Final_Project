package com.example.finalproject.controller;


import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final MyUserService myUserService;

    @GetMapping("/all")
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> customers = myUserService.getAllMyUsers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(myUserService.getMyUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody MyUser customer) {
        myUserService.addMyUser(customer);
        return ResponseEntity.status(200).body("User added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody MyUser updateUser) {
        myUserService.updateMyUser(updateUser, id);
        return ResponseEntity.status(200).body("User updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {

        return ResponseEntity.status(200).body("User deleted!");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> customerLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer logged in successfully", 200));
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> customerLogout() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer logout in successfully", 200));
    }


//    @PostMapping("/{user_id}/user/{customer_id}")
//    public ResponseEntity<String> assignCustomerAsUser(@PathVariable Integer user_id, @PathVariable Integer customer_id) {
//        userService.assignCustomerAsUser(customer_id, user_id);
//        return ResponseEntity.status(200).body("Assign customer as user!");
//
//    }
//
//
//    @PostMapping("/{user_id}/user/{store_id}")
//    public ResponseEntity<String> assignStoreAsUser(@PathVariable Integer user_id, @PathVariable Integer store_id) {
//        userService.assignCustomerAsUser(store_id, user_id);
//        return ResponseEntity.status(200).body("Assign store as user!");
//
//    }

}
