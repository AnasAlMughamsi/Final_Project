package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.*;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyOrderRepository;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final MyOrderRepository myOrderRepository;
    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;

    public List<MyOrder> getOrder()
    {
        return myOrderRepository.findAll();
    }
    public void addOrder(MyOrder order)
    {
        myOrderRepository.save(order);
    }
    public boolean updateOrder(Integer id,MyOrder order)
    {
        MyOrder order1 = myOrderRepository.findMyOrderById(id);
        if(order1==null)
            return false;
        order.setId(id);
        myOrderRepository.save(order);
        return true;
    }
    public boolean deleteOrder(Integer id)
    {
        MyOrder order = myOrderRepository.findMyOrderById(id);
        if(order==null)
            return false;
        myOrderRepository.delete(order);
        return true;
    }


    public void assignOrderToCustomer(Integer customer_id, Integer order_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);

        if(customer == null)  {
            throw new ApiException("Customer not found");
        }
//        if (customer.getUser().getId() != auth_id) {
//            throw new ApiException("Unauthorized");
//        }
//        if(myOrder == null) {
//            throw new ApiException("Order not found");
//        }
        customer.getMyOrders().add(myOrder);
        myOrder.setCustomer(customer);
        myOrderRepository.save(myOrder);
        customerRepository.save(customer);
    }

}
