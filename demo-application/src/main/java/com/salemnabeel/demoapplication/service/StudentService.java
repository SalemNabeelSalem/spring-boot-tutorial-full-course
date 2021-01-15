package com.salemnabeel.demoapplication.service;

import com.salemnabeel.demoapplication.model.Student;
import com.salemnabeel.demoapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {

        return studentRepository.findById(studentId);
    }

    public Student createStudent(Student studentRequest) {

        return studentRepository.save(studentRequest);
    }

    public Optional<Student> updateStudent(Long studentId, Student studentRequest) {

        Student student = studentRepository.findById(studentId).get();

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setDob(studentRequest.getDob());
        student.setAge(studentRequest.getAge());

        return Optional.of(studentRepository.save(student));
    }
}