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

    @ManyToMany
    @JoinColumn(name = "courseId")
    private List<Course> courses;

    public Lecturer() {
    }

}
