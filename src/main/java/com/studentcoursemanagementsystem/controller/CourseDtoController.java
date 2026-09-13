package com.studentcoursemanagementsystem.controller;

import com.studentcoursemanagementsystem.model.CourseWithStudentsDTO;
import com.studentcoursemanagementsystem.service.CourseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dto/courses")
public class CourseDtoController {

    @Autowired
    private CourseDtoService courseDtoService;

    @GetMapping("/name/{title}")
    public ResponseEntity<List<CourseWithStudentsDTO>> getCoursesWithStudentsByTitle(@PathVariable String title) {
        List<CourseWithStudentsDTO> courses = courseDtoService.getCoursesWithStudentsByTitle(title);
        if (courses == null || courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }
}
