package com.example.ss12.repository;

import com.example.ss12.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    boolean create(Product product);
    boolean update(Product product);
    boolean delete(int id);
}
