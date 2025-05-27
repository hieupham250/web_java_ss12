package com.example.ss12.repository;

import com.example.ss12.model.Bus;

import java.util.List;

public interface BusRepository {
    List<Bus> findAll();
    Bus findById(int id);
    boolean create(Bus bus);
    boolean update(Bus bus);
    boolean delete(int id);
}
