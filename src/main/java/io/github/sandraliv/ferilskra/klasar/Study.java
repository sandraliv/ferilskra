package io.github.sandraliv.ferilskra.klasar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "studies")
public class Study {
    @NotNull(message = "Study name cannot be null")
    private String studyName;

    @ElementCollection
    private Map<String, Double> courses; // Course name -> Grade mapping
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Study(String studyName) {
        this.studyName = studyName;
        this.courses = new HashMap<>();
    }

    public Study(){}

    // Add a course and its grade
    public void addCourse(String courseName, double grade) {
        courses.put(courseName, grade);
    }

    // Get grade for a specific course
    public Double getGrade(String courseName) {
        return courses.get(courseName);
    }

    // Calculate average grade for the study
    public double calculateAverageGrade() {
        if (courses.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (double grade : courses.values()) {
            total += grade;
        }
        return total / courses.size();
    }

    // Get the name of the study
    public String getStudyName() {
        return studyName;
    }

    // Get all courses and grades
    public Map<String, Double> getCourses() {
        return courses;
    }
}
