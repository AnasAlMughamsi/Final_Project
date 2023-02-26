package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public void addCustomer(Customer customer) {
        // TODO: I need to check if I need to add User before adding a customer or not
        User user = new User(); // creating new user for customer
        user.setRole("customer");
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer updateCustomer, Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null) {
            throw new ApiException("Customer not found, wrong id");
        }
        updateCustomer.setId(id);
        customerRepository.save(updateCustomer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null) {
            throw new ApiException("Wrong customer id");
        }
        customerRepository.delete(customer);
    }

    // TODO: assign customer to store

    public void assignOrderToCustomer(Integer customer_id, Integer order_id) {

    }
}
