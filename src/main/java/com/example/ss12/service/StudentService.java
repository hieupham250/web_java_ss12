package com.example.ss12.service;

import com.example.ss12.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    boolean isEmailExist(String email);
    boolean create(Student student);
    boolean update(Student student);
    boolean delete(int id);
}
