package com.example.finalproject.controller;

import com.example.finalproject.model.Store;
import com.example.finalproject.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private StoreService storeService;

    @GetMapping("/get")
    public ResponseEntity<List<Store>> getAllStore() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.status(200).body(stores);
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

    // TODO:
}
