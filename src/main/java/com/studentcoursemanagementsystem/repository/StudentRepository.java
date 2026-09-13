package com.studentcoursemanagementsystem.repository;

import com.studentcoursemanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByEmail(String email);

    @Query(value = "SELECT id, name, email, dob FROM (" +
            "    SELECT id, name, email, dob, " +
            "           ROW_NUMBER() OVER (PARTITION BY email ORDER BY id ASC) as row_num " +
            "    FROM stu.students" +
            ") ranked_students " +
            "WHERE row_num = 1",
            nativeQuery = true)
    List<Student> findUniqueStudentsByEmail();
}
