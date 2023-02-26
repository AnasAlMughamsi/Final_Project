package com.example.finalproject.controller;


import com.example.finalproject.model.User;
import com.example.finalproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> customers = userService.getAllUsers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User customer) {
        userService.addUser(customer);
        return ResponseEntity.status(200).body("User added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody User updateUser) {
        userService.updateUser(updateUser, id);
        return ResponseEntity.status(200).body("User updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted!");
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
