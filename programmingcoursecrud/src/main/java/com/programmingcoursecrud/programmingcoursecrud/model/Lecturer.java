package com.programmingcoursecrud.programmingcoursecrud.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinColumn(name = "courseId")
    private List<Course> courses;

    public Lecturer() {
    }

}
