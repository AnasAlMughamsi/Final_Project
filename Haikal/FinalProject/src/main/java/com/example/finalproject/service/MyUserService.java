package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.Store;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.StoreRepository;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;



    // TODO: need to check if I need these CRUD for MyUser !
    public List<MyUser> getAllMyUsers() {
        return userRepository.findAll();
    }

    public MyUser getMyUserById(Integer id) {
        MyUser user = userRepository.findMyUserById(id);
        if(user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public void addMyUser(MyUser user) {
        // TODO: I need to check if I need to add MyUser before adding a customer or not
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

    public void updateMyUser(MyUser updateMyUser, Integer id) {
        MyUser user = userRepository.findMyUserById(id);
        if(user == null) {
            throw new ApiException("user not found, wrong id");
        }
        updateMyUser.setId(id);
        userRepository.save(updateMyUser);
    }

    public void deleteMyUser(Integer id) {
        MyUser user = userRepository.findMyUserById(id);
        if(user == null) {
            throw new ApiException("Wrong user id");
        }
        userRepository.delete(user);
    }

    public void registerCustomer(MyUser user) {
//        if (user.getPassword().isBlank() || user.getPassword().isEmpty()) {
//            throw new ApiException("Password should be not empty and more than 3");
//        }
        user.setRole("customer");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public void registerStore(StoreDTO storeDTO) {


    }


//    public void assignStoreAsMyUser(Integer store_id, Integer user_id) {
//        MyUser user = userRepository.findMyUserById(user_id);
//        Store store = storeRepository.findStoreById(store_id);
//        if(user == null || store == null) {
//            throw new ApiException("Wrong user or store id");
//        }
//
//        store.setMyUser(user);
//        storeRepository.save(store);
//
//        user.getStoreList().add(store);
//        userRepository.save(user);
//    }
}
