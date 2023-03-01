package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("Customer added!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@AuthenticationPrincipal MyUser myUser, @Valid @RequestBody Customer updateCustomer) {
        customerService.updateCustomer(updateCustomer, myUser.getId());
        return ResponseEntity.status(200).body("Customer updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer deleted!");
    }

//    @PostMapping("/assign-store")
//    public ResponseEntity<String> assignCustomerToStore(@AuthenticationPrincipal MyUser myUser, Customer customer) {
//        customerService.assignCustomerToStore(customer ,myUser.getId());
//        return ResponseEntity.status(200).body("Assign customer to store!");
//    }

    @PostMapping("/assign-store/{store_id}")
    public ResponseEntity<String> assignCustomerToStore(@AuthenticationPrincipal MyUser myUser,
//                                                        Integer customer_id,
                                                        @PathVariable Integer store_id) {
        customerService.assignCustomerToStore(myUser.getId(),store_id);
        return ResponseEntity.status(200).body("Assign customer to store!");
    }


}
