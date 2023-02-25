package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.Store;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private StoreRepository storeRepository;
    private CustomerRepository customerRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Integer id) {
        Store store = storeRepository.findStoreById(id);
        if(store == null) {
            throw new ApiException("store not found");
        }
        return store;
    }

    public void addStore(Store store) {
        // TODO: I think need to check if I need to add User before adding a Store or not
        storeRepository.save(store);
    }

    public void updateStore(Store updateStore, Integer id) {
        Store store = storeRepository.findStoreById(id);
        if(store == null) {
            throw new ApiException("store not found, wrong id");
        }
        updateStore.setId(id);
        storeRepository.save(updateStore);
    }

    public void deleteStore(Integer id) {
        Store store = storeRepository.findStoreById(id);
        if(store == null) {
            throw new ApiException("Wrong store id");
        }
        storeRepository.delete(store);
    }

    // TODO: assign customer to store

    public void assignCustomerToStore(Integer customer_id, Integer store_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Store store = storeRepository.findStoreById(store_id);

        if(store == null || customer == null) {
            throw new ApiException("customer or store not found");
        }
        customer.setStore(store); // one to many - relation (one store to many users)
        store.getCustomers().add(customer);
        storeRepository.save(store);
    }

    // TODO: assign product to store

    public void assignProductToStore(Integer store_id, Integer product_id) {

    }

}
