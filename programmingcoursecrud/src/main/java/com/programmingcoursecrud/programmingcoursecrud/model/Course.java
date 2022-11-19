package com.programmingcoursecrud.programmingcoursecrud.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;

    @Column(name = "Description", length = 600)
    private String description;

    @Column(name = "StartingTime")
    private Time startingTime;

    @Column(name = "EndingTime")
    private Time endingTime;

    @Column(name = "DueDate")
    private Date dueDate;

    @ManyToMany
    @JoinColumn(name = "lecturerId") //should probably be PascalCase because that's how the column name is in the database
    private List<Lecturer> lecturers;

    public Course() {
    }

}
