package com.example.ss12.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SeatDTO {
    private int id;

    @NotBlank(message = "Tên ghế không được để trống")
    private String nameSeat;

    @NotNull(message = "Giá ghế không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá ghế phải lớn hơn 0")
    private BigDecimal price;

    @Min(value = 1, message = "ID xe phải lớn hơn 0")
    private int busId;

    @NotBlank(message = "Trạng thái ghế không được để trống")
    private String status;
}
