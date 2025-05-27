package com.example.ss12.service;

import com.example.ss12.model.Bus;

import java.util.List;

public interface BusService {
    List<Bus> findAll();
    Bus findById(int id);
    boolean create(Bus bus);
    boolean update(Bus bus);
    boolean delete(int id);
}
