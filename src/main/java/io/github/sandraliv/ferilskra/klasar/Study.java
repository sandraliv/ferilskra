package io.github.sandraliv.ferilskra.klasar;

import io.github.sandraliv.ferilskra.utils.SlugUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "studies")
public class Study {
    @NotNull(message = "Study name cannot be null")
    private String studyName;
    private String slug;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "study_courses",
            joinColumns = @JoinColumn(name = "study_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Study(String studyName) {
        this.studyName = studyName;
    }

    public Study(){}

    // Add a course and its grade
    public void addCourse(String courseName, double grade, int credits, String semester, int year) {
        courses.add(new Course(grade,courseName, credits, semester, year));
    }

    // Get grade for a specific course
    public Double getGrade(Course course) {
        return course.getGrade();
    }

    // Calculate average grade for the study
    public double calculateAverageGrade() {
        if (courses.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (Course grade : courses) {
            total += grade.getGrade();
        }
        return total / courses.size();
    }

    // Get the name of the study
    public String getStudyName() {
        return studyName;
    }

    // Get all courses and grades
    public List<Course> getCourses() {
        return courses;
    }

    public void setSlug(String slug) {
        //Url-safe version of studyName
    }

    public String getSlug() {
        return slug;
    }

    @PrePersist
    @PreUpdate
    public void generateSlug() {
        this.slug = SlugUtils.generateSlug(this.studyName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
