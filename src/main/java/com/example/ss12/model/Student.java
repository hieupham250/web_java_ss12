package com.example.ss12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private String email;
    private LocalDate dob;
}
