package com.example.ss12.service;

import com.example.ss12.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findByBusId(int busId);
    boolean save(Seat seat);
    boolean update(Seat seat);
    boolean deleteByBusId(int busId);
}
