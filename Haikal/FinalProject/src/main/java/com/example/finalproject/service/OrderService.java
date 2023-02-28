package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyUserRepository;
import com.example.finalproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;

    public List<MyOrder> getOrder()
    {
        return orderRepository.findAll();
    }
    public void addOrder(MyOrder order)
    {
        orderRepository.save(order);
    }
    public boolean updateOrder(Integer id,MyOrder order)
    {
        MyOrder order1 = orderRepository.findOrdersById(id);
        if(order1==null)
            return false;
        order.setId(id);
        orderRepository.save(order);
        return true;
    }
    public boolean deleteOrder(Integer id)
    {
        MyOrder order = orderRepository.findOrdersById(id);
        if(order==null)
            return false;
        orderRepository.delete(order);
        return true;
    }


    public void assignOrderToCustomer(MyOrder newOrder, Integer auth_id, Integer order_id) {
        MyUser myUser = myUserRepository.findMyUserById(auth_id);
        MyOrder myOrder = orderRepository.findOrdersById(order_id);
        Customer customer = customerRepository.findCustomerById(myUser.getId());
        if(customer == null || myOrder == null)  {
            throw new ApiException("Customer or order not found");
        }
//        newOrder.setCustomer_order(customer);
        customer.getOrderList().add(newOrder);
        orderRepository.save(newOrder);
    }
}
