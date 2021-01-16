package com.salemnabeel.demoapplication.config;

import com.salemnabeel.demoapplication.model.Student;
import com.salemnabeel.demoapplication.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student salem = new Student(
                "Salem Nabeel",
                "salemnabeel@gmail.com",
                LocalDate.of(1994, Month.SEPTEMBER, 01)
            );

            Student ammar = new Student(
                "Ammar Ali",
                "ammarali@outlook.com",
                LocalDate.of(1995, Month.APRIL, 02)
            );

            Student ali = new Student(
                "Ali Omer",
                "aliomer@hotmail.com",
                LocalDate.of(2001, Month.MAY, 03)
            );

            studentRepository.saveAll(List.of(salem, ammar, ali));
        };
    }
}