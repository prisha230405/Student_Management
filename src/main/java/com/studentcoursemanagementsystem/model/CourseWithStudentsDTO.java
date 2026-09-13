package com.studentcoursemanagementsystem.model;

import java.util.List;

public class CourseWithStudentsDTO {

    private Long id;
    private String title;
    private String description;
    private List<String> studentNames;

    public CourseWithStudentsDTO() {
    }

    public CourseWithStudentsDTO(Long id, String title, String description, List<String> studentNames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studentNames = studentNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}
