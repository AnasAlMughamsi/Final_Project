package com.example.finalproject.service;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Store;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;

    public void RegisterCustomer(CustomerDTO customerDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(customerDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);

        MyUser newUser = myUserRepository.save(myUser);
//        myUserRepository.save(myUser);

        myUser.setRole("customer");
        Customer customer = new Customer();
        customer.setUser(newUser); // saved as new user
        customer.setGender(customerDTO.getGender());
        customer.setEmail(customerDTO.getEmail());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(customer);

    }

    public void RegisterStore(StoreDTO storeDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(storeDTO.getStoreName());
        String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);

        MyUser newUser = myUserRepository.save(myUser);

        myUser.setRole("store");
        Store store = new Store();
        store.setUser(newUser);

    }
}
