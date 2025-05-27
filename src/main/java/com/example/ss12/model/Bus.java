package com.example.ss12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bus {
    private int id;
    private String licensePlate;
    private String busType;
    private int rowSeat;
    private int colSeat;
    private int totalSeat;
    private String image;
}
