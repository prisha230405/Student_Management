package com.studentcoursemanagementsystem.service;

import com.studentcoursemanagementsystem.exception.StudentAlreadyExistsException;
import com.studentcoursemanagementsystem.model.Student;
import com.studentcoursemanagementsystem.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        List<Student> studentList = studentRepository.findByEmail(student.getEmail());
        log.info("found list of student {}", studentList.size());

        if (!studentList.isEmpty()) {
            throw new StudentAlreadyExistsException("Student already registered with this email.");
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findUniqueStudentsByEmail();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setDob(updatedStudent.getDob());
        return studentRepository.save(existing);
    }

    public boolean deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) return false;
        studentRepository.deleteById(id);
        return true;
    }
}
