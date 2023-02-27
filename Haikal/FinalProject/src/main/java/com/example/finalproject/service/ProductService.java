package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.ProductDetailsDTO;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.ProductDetails;
import com.example.finalproject.repository.ProductDetailsRepository;
import com.example.finalproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void addProductDetails(ProductDetailsDTO productDTO) {
        Product product = productRepository.findProductById(productDTO.getProduct_id());
        if(product == null) {
            throw new ApiException("product not found!");
        }

        ProductDetails productDetails =new ProductDetails(null, productDTO.getQuantity(), product);
        productDetailsRepository.save(productDetails);
    }

    public void updateProductDetails(ProductDetailsDTO updateProductDTO) {
        ProductDetails productDetails = productDetailsRepository.findProductDetailsById(updateProductDTO.getProduct_id());
        if(productDetails == null) {
            throw new ApiException("product not found!");
        }
//        updateProductDTO.setProduct_id(productDetails.getId());
        productDetails.setQuantity(updateProductDTO.getQuantity());
        productDetailsRepository.save(productDetails);
    }

    public void deleteProductDetails(Integer id) {
        ProductDetails productDetails = productDetailsRepository.findProductDetailsById(id);
        if(productDetails == null) {
            throw new ApiException("product details not found!");
        }
        productDetailsRepository.delete(productDetails);
    }

    public boolean updateProduct(Integer id, Product product) {
        Product product1 = productRepository.findProductById(id);
        if(product1==null) {
            throw new ApiException("product not found!");
        }
        product.setId(id);
        productRepository.save(product);
        return true;
    }
    public boolean deleteProduct(Integer id) {
        Product product = productRepository.findProductById(id);
        if(product==null) {
            throw new ApiException("product not found!");
        }
        productRepository.delete(product);
        return true;
    }
}
