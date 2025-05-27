package com.example.ss12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {
    private int id;
    private String nameSeat;
    private BigDecimal price;
    private int busId;
    private String status;
}
