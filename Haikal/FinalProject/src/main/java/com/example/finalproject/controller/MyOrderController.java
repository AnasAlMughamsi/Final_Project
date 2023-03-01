package com.example.finalproject.controller;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.OrderService;
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
    private final OrderService orderService;
    @GetMapping("/get")
    public ResponseEntity getOrder()
    {
        List<MyOrder> orderList = orderService.getOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@Valid @RequestBody MyOrder order)
    {
        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order added !");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@Valid @RequestBody MyOrder order)
    {
        boolean isAvailable = orderService.updateOrder(id, order);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Order updated !");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id)
    {
        boolean isAvailable = orderService.deleteOrder(id);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted !");
    }


    // TODO: assign order to customer
    @PostMapping("/add-order/{customer_id}/{order_id}")
    public ResponseEntity<String> assignOrderToCustomer(@PathVariable Integer customer_id, @PathVariable Integer order_id) {
        orderService.assignOrderToCustomer(customer_id, order_id);
        return ResponseEntity.status(HttpStatus.OK).body("Assign product to store");
    }
}
