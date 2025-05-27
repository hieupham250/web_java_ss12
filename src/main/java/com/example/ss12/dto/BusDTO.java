package com.example.ss12.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class BusDTO {
    private int id;

    @NotBlank(message = "Biển số xe không được để trống")
    private String licensePlate;

    @NotBlank(message = "Loại xe không được để trống")
    private String busType;

    @Min(value = 1, message = "Số hàng ghế phải lớn hơn 0")
    private int rowSeat;

    @Min(value = 1, message = "Số cột ghế phải lớn hơn 0")
    private int colSeat;

    private int totalSeat;

    @NotBlank(message = "Ảnh xe không được để trống")
    private String image;
}
