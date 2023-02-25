package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;


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

    public void deleteCustomer(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new ApiException("Wrong user id");
        }
        userRepository.delete(user);
    }
}
