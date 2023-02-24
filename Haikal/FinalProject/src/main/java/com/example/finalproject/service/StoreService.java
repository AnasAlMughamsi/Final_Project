package com.example.finalproject.service;

import com.example.finalproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private StoreRepository storeRepository;
}
