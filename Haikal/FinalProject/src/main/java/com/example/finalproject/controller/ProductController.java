package com.example.finalproject.controller;

import com.example.finalproject.model.Product;
import com.example.finalproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController
{
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProduct()
    {
        List<Product>productList = productService.getProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product)
    {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product added !");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product)
    {
        boolean isAvailable = productService.updateProduct(id, product);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Product updated !");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id)
    {
        boolean isAvailable = productService.deleteProduct(id);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted !");
    }
}
