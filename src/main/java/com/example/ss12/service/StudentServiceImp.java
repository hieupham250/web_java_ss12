package com.example.ss12.service;

import com.example.ss12.model.Student;
import com.example.ss12.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean isEmailExist(String email) {
        return studentRepository.isEmailExist(email);
    }

    @Override
    public boolean create(Student student) {
        return studentRepository.create(student);
    }

    @Override
    public boolean update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public boolean delete(int id) {
        return studentRepository.delete(id);
    }
}
