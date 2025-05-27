package com.example.ss12.service;

import com.example.ss12.model.Seat;
import com.example.ss12.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImp implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> findByBusId(int busId) {
        return seatRepository.findByBusId(busId);
    }

    @Override
    public boolean save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public boolean update(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public boolean deleteByBusId(int busId) {
        return seatRepository.deleteByBusId(busId);
    }
}
