package com.example.ss12.repository;

import com.example.ss12.connection.ConnectionDB;
import com.example.ss12.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImp implements StudentRepository {
    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_all_student()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate()
                );
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return students;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_student_by_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return student;
    }

    @Override
    public boolean isEmailExist(String email) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call is_exist_email(?)}");
            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
              return rs.getInt("is_exist") == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean create(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_student(?, ?, ?)}");
            cstmt.setString(1, student.getName());
            cstmt.setString(2, student.getEmail());
            cstmt.setString(3, student.getDob().toString());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call update_student(?, ?, ?, ?)}");
            cstmt.setInt(1, student.getId());
            cstmt.setString(2, student.getName());
            cstmt.setString(3, student.getEmail());
            cstmt.setString(4, student.getDob().toString());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_student(?)}");
            cstmt.setInt(1, id);
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }
}
