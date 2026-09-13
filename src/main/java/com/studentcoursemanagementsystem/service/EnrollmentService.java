package com.studentcoursemanagementsystem.service;

import com.studentcoursemanagementsystem.model.Course;
import com.studentcoursemanagementsystem.model.Enrollment;
import com.studentcoursemanagementsystem.model.Student;
import com.studentcoursemanagementsystem.repository.CourseRepository;
import com.studentcoursemanagementsystem.repository.EnrollmentRepository;
import com.studentcoursemanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public String enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) return "ERROR: Student not found";

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) return "ERROR: Course not found";

        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            return "ERROR: Student already enrolled in this course";
        }

        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        enrollmentRepository.save(enrollment);
        return "SUCCESS";
    }

    public List<Enrollment> getCoursesByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getStudentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public boolean cancelEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) return false;
        enrollmentRepository.deleteById(enrollmentId);
        return true;
    }
}
