package com.example.ss12.service;

import com.example.ss12.model.Bus;
import com.example.ss12.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImp implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Override
    public Bus findById(int id) {
        return busRepository.findById(id);
    }

    @Override
    public boolean create(Bus bus) {
        return busRepository.create(bus);
    }

    @Override
    public boolean update(Bus bus) {
        return busRepository.update(bus);
    }

    @Override
    public boolean delete(int id) {
        return busRepository.delete(id);
    }
}
