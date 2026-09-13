package com.studentcoursemanagementsystem.service;

import com.studentcoursemanagementsystem.exception.CourseAlreadyExistsException;
import com.studentcoursemanagementsystem.model.Course;
import com.studentcoursemanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        List<Course> courseList = courseRepository.findByTitle(course.getTitle());

        if (!courseList.isEmpty()) {
            throw new CourseAlreadyExistsException("Course title is already exists.");
        }
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existing = courseRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTitle(updatedCourse.getTitle());
        existing.setDescription(updatedCourse.getDescription());
        existing.setCredits(updatedCourse.getCredits());
        return courseRepository.save(existing);
    }

    public boolean deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) return false;
        courseRepository.deleteById(id);
        return true;
    }
}
