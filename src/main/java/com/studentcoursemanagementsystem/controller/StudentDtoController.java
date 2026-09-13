package com.studentcoursemanagementsystem.controller;

import com.studentcoursemanagementsystem.service.StudentDtoService;
import com.studentcoursemanagementsystem.model.StudentWithCoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dto/students")
public class StudentDtoController {

    @Autowired
    private StudentDtoService studentDtoService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentWithCoursesDTO> getStudentWithCourses(@PathVariable Long id) {
        StudentWithCoursesDTO studentDTO = studentDtoService.getStudentWithCourses(id);
        if (studentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentDTO);
    }
}
