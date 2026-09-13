package com.studentcoursemanagementsystem.service;

import com.studentcoursemanagementsystem.model.Course;
import com.studentcoursemanagementsystem.model.CourseWithStudentsDTO;
import com.studentcoursemanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDtoService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseWithStudentsDTO> getCoursesWithStudentsByTitle(String title) {
        List<Course> courses = courseRepository.findByTitle(title);

        return courses.stream().map(course -> {
            List<String> studentNames = course.getEnrollments().stream()
                    .map(enrollment -> enrollment.getStudent().getName())
                    .collect(Collectors.toList());

            return new CourseWithStudentsDTO(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    studentNames
            );
        }).collect(Collectors.toList());
    }
}
