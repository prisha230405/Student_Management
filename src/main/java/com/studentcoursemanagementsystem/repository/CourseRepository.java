package com.studentcoursemanagementsystem.repository;

import com.studentcoursemanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitle(String title);
    @Query(value = "SELECT id, title, description, credits FROM (" +
            "    SELECT id, title, description, credits, " +
            "           ROW_NUMBER() OVER (PARTITION BY title ORDER BY id ASC) AS row_num " +
            "    FROM course" +
            ") ranked_courses " +
            "WHERE row_num = 1",
            nativeQuery = true)
    List<Course> findUniqueCoursesByTitle();
}
