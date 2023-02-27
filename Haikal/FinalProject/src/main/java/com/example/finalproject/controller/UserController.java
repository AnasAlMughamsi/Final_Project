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
    public ResponseEntity customerLogin() {
        return ResponseEntity.status(HttpStatus.OK).body("Customer logged in successfully");
    }
    @PostMapping("/logout")
    public ResponseEntity customerLogout() {
        return ResponseEntity.status(HttpStatus.OK).body("Customer logout in successfully");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser) {
        myUserService.registerCustomer(myUser);
        return ResponseEntity.status(HttpStatus.OK).body("good");
    }



//    @PostMapping("/{user_id}/user/{store_id}")
//    public ResponseEntity<String> assignStoreAsUser(@PathVariable Integer user_id, @PathVariable Integer store_id) {
//        userService.assignCustomerAsUser(store_id, user_id);
//        return ResponseEntity.status(200).body("Assign store as user!");
//
//    }

}
