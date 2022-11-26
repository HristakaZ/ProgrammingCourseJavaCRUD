package com.programmingcoursecrud.programmingcoursecrud.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name", nullable = false)
    @NotEmpty(message = "Name is required.")
    private String name;

    @Column(name = "Age")
    @NotNull(message = "Age is required.")
    @Min(value = 18, message = "The lecturer must be at least 18 years old.")
    private Integer age;

    @Column(name = "Description", length = 600)
    @Size(min = 1, max = 600, message = "The description must be between 1 and 600 characters.")
    private String description;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}