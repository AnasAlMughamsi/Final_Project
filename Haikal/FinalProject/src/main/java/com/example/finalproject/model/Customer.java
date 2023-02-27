package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @NotEmpty(message = "email is required")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotEmpty(message = "phone number is required")
    private String phoneNumber;

    @Column(name = "dateOfBirth")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dateOfBirth;
    @NotEmpty(message = "gender is required")
    @Pattern(regexp = "male|female")
    @Column(columnDefinition = "varchar(10) not null check (gender= 'male' or gender= 'female')")
    private String gender;


    //  Relationships
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer_order")
    private List<MyOrder> orderList;

    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;




}
