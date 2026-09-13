package com.studentcoursemanagementsystem.model;

import java.util.List;

public class StudentWithCoursesDTO {
    private Long id;
    private String name;
    private String email;
    private List<String> courseNames;

    public StudentWithCoursesDTO() {}

    public StudentWithCoursesDTO(Long id, String name, String email, List<String> courseNames) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseNames = courseNames;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getCourseNames() { return courseNames; }
    public void setCourseNames(List<String> courseNames) { this.courseNames = courseNames; }
}
