package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
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

    public void RegisterCustomers(CustomerDTO customerDTO) {

//        MyUser myUser = new MyUser();
//        myUser.setRole("customer");
//        myUser.setUsername(customerDTOuser.getUsername());
//        String hashedPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
//        myUser.setPassword(hashedPassword);
//
//        MyUser createdUser = myUserRepository.save(myUser);
//
////         create customer
//
//        Customer customer = new Customer();
//        Customer customer1 = new Customer(null, customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getName(), customerDTO.getEmail(),
//                customerDTO.getPhoneNumber(), customerDTO.getDateOfBirth(), customerDTO.getGender(), null, null, myUser);
//        Customer customer = customerRepository.findCustomerById(customerDTO.getUser_id());
//        customer.setUsername(createdUser.getUsername());
//        customer.setPassword(createdUser.getPassword());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());
//        customer.setGender(customerDTO.getGender());
//        customer.setCustomer_user(createdUser);
//        customerRepository.save(customer1);
// ==========================================================================================================
//        MyUser myUser = new MyUser();
//        myUser.setRole("customer");
//        myUser.setUsername(customerDTO.getUsername());
//        String hashedPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
//        myUser.setPassword(hashedPassword);
////        myUser.setCustomer(null);
//        Customer customer = new Customer();
//        customer.setEmail(customerDTO.getEmail());
//        customer.setDateOfBirth(customerDTO.getDateOfBirth());
//        customer.setPhoneNumber(customerDTO.getPhoneNumber());
//        customer.setGender(customerDTO.getGender());
//
//        Customer createdCustomer = customerRepository.save(customer);
//        MyUser newUser = myUserRepository.save(myUser);
//
//        customerRepository.save(createdCustomer);
//        myUserRepository.save(newUser);

    }

    public void registerCustomer(MyUser user) {
        if (user.getPassword().isBlank() || user.getPassword().isEmpty()) {
            throw new ApiException("Password should be not empty and more than 3");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println("user id: " + user.getId());
        myUserRepository.save(user);
    }

    public void registerStore(StoreDTO storeDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(storeDTO.getStoreName());
        String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);

        MyUser newUser = myUserRepository.save(myUser);

        myUser.setRole("store");
        Store store = new Store();
        store.setStore_user(newUser);

    }
}
