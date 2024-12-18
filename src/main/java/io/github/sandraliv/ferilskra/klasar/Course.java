package io.github.sandraliv.ferilskra.klasar;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {
    @NotNull
    private String name;
    @NotNull
    private Double grade;
    @NotNull
    private Integer credits;
    @NotNull
    private String semester;
    @NotNull
    private Integer year;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Course(Double grade, String name, Integer credits, String semester, Integer year) {
        this.grade = grade;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.year = year;
    }

    public Course() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
