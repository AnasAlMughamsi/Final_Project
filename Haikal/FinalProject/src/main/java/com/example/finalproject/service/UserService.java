package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.Store;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.StoreRepository;
import com.example.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;



    // TODO: need to check if I need these CRUD for User !
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public void addUser(User user) {
        // TODO: I need to check if I need to add User before adding a customer or not
        Customer newCustomer = new Customer();
        Store newStore = new Store();

        if(user.getRole().equals("customer")) {
            user.setRole("customer");
            customerRepository.save(newCustomer);
        } else if (user.getRole().equals("store")) {
            user.setRole("store");
            storeRepository.save(newStore);
        }
        userRepository.save(user);
    }

    public void updateUser(User updateUser, Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new ApiException("user not found, wrong id");
        }
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new ApiException("Wrong user id");
        }
        userRepository.delete(user);
    }

    // Assign customer as user
    // Assign store as user

//    public void assignCustomerAsUser(Integer customer_id, Integer user_id) {
//        User user = userRepository.findUserById(user_id);
//        Customer customer = customerRepository.findCustomerById(customer_id);
//        if(user == null || customer == null) {
//            throw new ApiException("Wrong user or customer id");
//        }
//        customer.setUser(user);
//        customerRepository.save(customer);
//
//        user.getCustomerList().add(customer);
//        userRepository.save(user);
//    }
//    public void assignStoreAsUser(Integer store_id, Integer user_id) {
//        User user = userRepository.findUserById(user_id);
//        Store store = storeRepository.findStoreById(store_id);
//        if(user == null || store == null) {
//            throw new ApiException("Wrong user or store id");
//        }
//
//        store.setUser(user);
//        storeRepository.save(store);
//
//        user.getStoreList().add(store);
//        userRepository.save(user);
//    }
}
