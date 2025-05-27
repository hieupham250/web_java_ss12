package com.example.ss12.service;

import com.example.ss12.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    boolean create(Product product);
    boolean update(Product product);
    boolean delete(int id);
}
