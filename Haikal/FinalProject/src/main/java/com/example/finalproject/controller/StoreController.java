package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.model.Store;
import com.example.finalproject.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStore() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.status(200).body(stores);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(storeService.getStoreById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStore(@Valid @RequestBody Store store) {
        storeService.addStore(store);
        return ResponseEntity.status(200).body("Store added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStore(@PathVariable Integer id, @Valid @RequestBody Store updateStore) {
        storeService.updateStore(updateStore, id);
        return ResponseEntity.status(200).body("Store updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id );
        return ResponseEntity.status(200).body("Store deleted!");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> storeLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Store logged in successfully!", 201));
    }

    // TODO: assign customer to store

    @PostMapping("/{store_id}/store/{customer_id}")
    public ResponseEntity<String> assignCustomerAsUser(@PathVariable Integer store_id, @PathVariable Integer customer_id) {
        storeService.assignCustomerToStore(store_id, customer_id);
        return ResponseEntity.status(200).body("Assign customer to store!");
    }
}
