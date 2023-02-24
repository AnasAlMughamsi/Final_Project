package com.example.finalproject.service;

import com.example.finalproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
}
