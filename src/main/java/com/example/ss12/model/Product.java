package com.example.ss12.model;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;
}
