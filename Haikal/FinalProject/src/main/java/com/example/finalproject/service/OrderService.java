package com.example.finalproject.service;

import com.example.finalproject.model.Order;
import com.example.finalproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

    public List<Order> getOrder()
    {
        return orderRepository.findAll();
    }
    public void addOrder(Order order)
    {
        orderRepository.save(order);
    }
    public boolean updateOrder(Integer id,Order order)
    {
        Order order1 = orderRepository.findOrdersById(id);
        if(order1==null)
            return false;
        order.setId(id);
        orderRepository.save(order);
        return true;
    }
    public boolean deleteOrder(Integer id)
    {
        Order order = orderRepository.findOrdersById(id);
        if(order==null)
            return false;
        orderRepository.delete(order);
        return true;
    }
}
