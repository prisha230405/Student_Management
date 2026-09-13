package com.studentcoursemanagementsystem.controller;

import com.studentcoursemanagementsystem.model.Enrollment;
import com.studentcoursemanagementsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<String> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        String result = enrollmentService.enrollStudent(studentId, courseId);
        if (result.startsWith("ERROR")) return ResponseEntity.badRequest().body(result);
        return ResponseEntity.ok("Enrollment successful");
    }

    @GetMapping("/student/{student_id}")
    public List<Enrollment> getCoursesByStudent(@PathVariable Long studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }

    @GetMapping("/course/{course_id}")
    public List<Enrollment> getStudentsByCourse(@PathVariable Long courseId) {
        return enrollmentService.getStudentsByCourse(courseId);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<String> cancelEnrollment(@PathVariable Long enrollmentId) {
        boolean cancelled = enrollmentService.cancelEnrollment(enrollmentId);
        if (!cancelled) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Enrollment cancelled");
    }
}
