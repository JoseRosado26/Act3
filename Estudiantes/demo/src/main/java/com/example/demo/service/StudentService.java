package com.example.demo.service;

import com.example.demo.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student s);
    void deleteById(Long id);
}