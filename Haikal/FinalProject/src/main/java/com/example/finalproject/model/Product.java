package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Product name should be not empty!")
    @Size(min = 3,message = "Product name most be more than 3 character!")
    private String productName;
    @NotNull(message = "Product stock can not be null")
    private Integer productStock;
    @NotNull(message = "Price can not be null")
    @Positive( message = "Price must be positive!")
    private double price;
    @NotEmpty(message = "Product car name should be not empty!")
    @Size(min = 3,message = "Product car name most be more than 3 character!")
    private String productCarName;
    @NotEmpty(message = "Company name should be not empty!")
    @Size(min = 3,message = "Company name most be more than 3 character!")
    private String companyName;
    @NotNull(message = "Product model year can not be null")
    @Min(value = 1950,message = "Product model year most be more than 1950 character!")
    private Integer productModelYear;
    @NotEmpty(message = "Product color should be not empty!")
    @Size(min = 2,message = "Product color most be more than 2 character!")
    private String productColor;
    private boolean isAvailable;
    @NotEmpty(message = "Product class should be not empty!")
    private String productClass;
    @NotEmpty(message = "Product status should be not empty!")
//    @Pattern(regexp = "(?:^|\\\\W)Good(?:$|\\\\W)|(?:^|\\\\W)Medium(?:$|\\\\W)|(?:^|\\\\W)Bad(?:$|\\\\W)")
    @Pattern(regexp = "(Good|Medium|Bad)")
    private String productStatus;
    @NotEmpty(message = "Product image URL should be not empty!")
    private String productImageUrl;

    @ManyToMany(mappedBy = "productList")
    private List<Order> orderList;

    @ManyToOne
    @JoinColumn(name = "product_store", referencedColumnName = "id")
    @JsonIgnore
    private Store store;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    @PrimaryKeyJoinColumn
    private ProductDetails productDetails;
}
