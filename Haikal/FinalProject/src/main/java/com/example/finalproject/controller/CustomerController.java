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

    @GetMapping("/get")
    public ResponseEntity getCustomerById(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(customerService.getCustomerById(user.getId()));
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
//        customerService.addCustomer(customer);
//        return ResponseEntity.status(200).body("Customer added!");
//    }

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

//    @PostMapping("/addCustomer")
//    public ResponseEntity assignCustomerToUser(@RequestBody @Valid CustomerDTO customerDTO, @AuthenticationPrincipal MyUser myUser) {
//        customerService.assignCustomerToUser(customerDTO, myUser.getId());
//        return ResponseEntity.status(HttpStatus.CREATED).body("assign customer as user registered!");
//    }

    // TODO: assign order to customer
//    @PostMapping("/customer/{order_id}")
//    public ResponseEntity assignOrderToCustomer(@RequestBody @Valid Customer customer, @AuthenticationPrincipal MyUser myUser, @PathVariable Integer order_id) {
//        customerService.assignOrderToCustomer(customer, myUser.getId());
//        return ResponseEntity.status(HttpStatus.CREATED).body("assign customer as user registered!");
//    }

//    @PostMapping("/assign-store")
//    public ResponseEntity<String> assignCustomerToStore(@AuthenticationPrincipal MyUser myUser, Customer customer) {
//        customerService.assignCustomerToStore(customer ,myUser.getId());
//        return ResponseEntity.status(200).body("Assign customer to store!");
//    }


}
