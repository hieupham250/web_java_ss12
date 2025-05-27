package com.example.ss12.repository;

import com.example.ss12.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findById(int id);
    boolean isEmailExist(String email);
    boolean create(Student student);
    boolean update(Student student);
    boolean delete(int id);
}
