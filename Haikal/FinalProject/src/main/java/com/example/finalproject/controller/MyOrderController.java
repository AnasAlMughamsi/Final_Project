package com.example.finalproject.controller;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class MyOrderController
{
    private final MyOrderService myOrderService;
    @GetMapping("/all")
    public ResponseEntity getAllOrder() {
        List<MyOrder> orderList = myOrderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(myOrderService.getOrderById(id));
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@Valid @RequestBody MyOrder order)
    {
        myOrderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order added !");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@Valid @RequestBody MyOrder order)
    {
        boolean isAvailable = myOrderService.updateOrder(id, order);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Order updated !");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id)
    {
        boolean isAvailable = myOrderService.deleteOrder(id);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted !");
    }


    // TODO: assign order to customer
    @PostMapping("/order-customer/{customer_id}/{order_id}")
    public ResponseEntity<String> assignOrderToCustomer(@PathVariable Integer customer_id, @PathVariable Integer order_id,
                                                        @AuthenticationPrincipal MyUser myUser) {
        myOrderService.assignOrderToCustomer(customer_id, order_id, myUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Assign order to customer");
    }

    @PostMapping("/order-product/{product_id}/{order_id}")
    public ResponseEntity<String> assignOrderToProduct(@PathVariable Integer product_id, @PathVariable Integer order_id,
                                                        @AuthenticationPrincipal MyUser myUser) {
        myOrderService.assignOrderToProduct(product_id, order_id, myUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Assign order to product");
    }
}