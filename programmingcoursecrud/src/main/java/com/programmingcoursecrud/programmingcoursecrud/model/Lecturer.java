package com.programmingcoursecrud.programmingcoursecrud.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "Description", length = 600)
    private String description;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses;

    public Lecturer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
