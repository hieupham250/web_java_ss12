package com.example.ss12.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentDTO {
    private int id;
    @NotBlank(message = "Tên không được để trống!")
    private String name;
    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Email không hợp lệ!")
    private String email;
    @NotNull(message = "Ngày sinh không được để trống!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
