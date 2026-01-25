package com.ravi.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserDtls user;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @Transient //used so that the table doesn't create the column for this and can be used just for display in the frontend.
    private Double totalPrice;

    @Transient
    private Double totalOrderPrice;
}
