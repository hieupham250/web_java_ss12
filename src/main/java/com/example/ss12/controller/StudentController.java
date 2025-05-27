package com.example.ss12.controller;

import com.example.ss12.dto.StudentDTO;
import com.example.ss12.model.Student;
import com.example.ss12.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showStudentList(Model model) {
        List<Student> students = studentService.findAll();
        System.out.println("111111          " + students.size());
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("addStudent")
    public String showAddForm(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "addStudent";
    }

    @PostMapping("addStudent")
    public String addStudent(@Valid @ModelAttribute("studentDTO") StudentDTO studentDTO, BindingResult result) {
        if (studentService.isEmailExist(studentDTO.getEmail())) {
            result.rejectValue("email", "email.exist", "Email đã tồn tại!");
        }

        if (result.hasErrors()) {
            return "addStudent";
        }

        // Chuyển đổi DTO sang Model
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setDob(studentDTO.getDob());

        studentService.create(student);
        return "redirect:/students";
    }

    @GetMapping("updateStudent")
    public String showUpdateForm(int id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            return "redirect:/students";
        }

        // Chuyển từ model sang DTO
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDob(student.getDob());

        model.addAttribute("studentDTO", studentDTO);
        return "updateStudent";
    }

    @PostMapping("updateStudent")
    public String updateStudent(@Valid @ModelAttribute("studentDTO") StudentDTO studentDTO, BindingResult result) {
        Student student = studentService.findById(studentDTO.getId());
        if (student == null) {
            return "redirect:/students";
        }

        if (!student.getEmail().equals(studentDTO.getEmail()) && studentService.isEmailExist(studentDTO.getEmail())) {
            result.rejectValue("email", "email.exist", "Email đã tồn tại!");
        }

        if (result.hasErrors()) {
            return "updateStudent";
        }

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setDob(studentDTO.getDob());
        studentService.update(student);

        return "redirect:/students";
    }

    @GetMapping("deleteStudent")
    public String deleteStudent(int id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}
