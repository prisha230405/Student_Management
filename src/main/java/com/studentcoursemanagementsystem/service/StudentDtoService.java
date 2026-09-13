package com.studentcoursemanagementsystem.service;

import com.studentcoursemanagementsystem.model.StudentWithCoursesDTO;
import com.studentcoursemanagementsystem.model.Student;
import com.studentcoursemanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDtoService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentWithCoursesDTO getStudentWithCourses(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) return null;

        List<String> courseNames = student.getEnrollments().stream()
                .map(enrollment -> enrollment.getCourse().getTitle())
                .collect(Collectors.toList());

        return new StudentWithCoursesDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                courseNames
        );
    }
}
