package com.salemnabeel.demoapplication.controller;

import com.salemnabeel.demoapplication.model.Student;
import com.salemnabeel.demoapplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {

        try {

            List<Student> students = studentService.getAllStudents();

            if (students.isEmpty()) {

                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

            } else {

                return new ResponseEntity<>(students, HttpStatus.OK);
            }
        } catch (Exception ex) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) {

        try {

            Optional<Student> studentData = studentService.getStudentById(studentId);

            if (studentData.isPresent()) {

                return new ResponseEntity<>(studentData.get(), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentRequest) {

        try {

            Student student = studentService.createStudent(studentRequest);

            return new ResponseEntity<>(student, HttpStatus.OK);

        } catch (Exception ex) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId,
                                                 @RequestBody Student studentRequest) {

        try {

            Optional<Student> studentData = studentService.getStudentById(studentId);

            if (studentData.isPresent()) {

                return new ResponseEntity<>(
                    studentService.updateStudent(studentId, studentRequest).get(),
                    HttpStatus.OK
                );

            } else {

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long studentId) {

        try {

            Optional<Student> studentData = studentService.getStudentById(studentId);

            if (studentData.isPresent()) {

                studentService.deleteStudent(studentId);

                return new ResponseEntity<>(null, HttpStatus.OK);

            } else {

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}