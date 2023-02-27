package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;

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




//    public void assignCustomerToUser(CustomerDTO customerDTO, Integer auth_id) {
//        MyUser myUser = myUserRepository.findMyUserById(auth_id);
//        if(myUser == null) {
//            throw new ApiException("user not found!");
//        } else if(myUser.getCustomer() == null) {
//            throw new ApiException("Customer not found!");
//        }
//        myUserRepository.save(myUser);
////        Customer customer = new Customer(null, customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getPhoneNumber(),
////                customerDTO.getDateOfBirth(), customerDTO.getGender(), myUser,null, null);
//        Customer customer = new Customer(null, customerDTO.getFirstName(), customerDTO.getLastName(), myUser, null, null);
//        customerRepository.save(customer);
//    }
    public void assignCustomerToUser(CustomerDTO customerDTO, Integer auth_id) {
        MyUser myUser = myUserRepository.findMyUserById(auth_id);
        if (myUser == null) {
            throw new ApiException("user ID not found");
        } else if (myUser.getCustomer() != null) {
            throw new ApiException("Customer Already Exist!!!!");
        }
        myUserRepository.save(myUser);
        Customer customer = new Customer(null, customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getPhoneNumber(),
            customerDTO.getDateOfBirth(), customerDTO.getGender(), myUser,null, null);
//        Customer customer = new Customer(null, customerDTO.getFirstName(), customerDTO.getLastName(), myUser, null, null);
        customerRepository.save(customer);
    }


}
