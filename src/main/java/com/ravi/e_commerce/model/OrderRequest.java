package com.ravi.e_commerce.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class OrderRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private String paymentType;
}
