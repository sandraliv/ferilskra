package io.github.sandraliv.ferilskra.controllers;

import io.github.sandraliv.ferilskra.klasar.Course;
import io.github.sandraliv.ferilskra.klasar.Study;
import io.github.sandraliv.ferilskra.services.studyServiceInterface;
import io.github.sandraliv.ferilskra.utils.SlugUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studies")
public class studyController {
    private final studyServiceInterface studyService;

    public studyController(studyServiceInterface studyService) {
        this.studyService = studyService;
    }

    //================================================================================
    // POST Methods
    //================================================================================

    /**
     *
     * @param theStudy name of my study
     * @return ok if studyName was not empty
     */
    @PostMapping
    public ResponseEntity<String> addStudy(@Valid @RequestBody Study theStudy){
        Study study = studyService.save(theStudy);
        return ResponseEntity.ok("Thanks for adding " + study.getStudyName());
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Study> addCourse(
            @PathVariable Integer id,
            @Valid @RequestBody Course course) {

        Optional<Study> optionalStudy = studyService.findById(id);
        if (optionalStudy.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Study study = optionalStudy.get();
        if (study.getCourses().contains(course)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        study.getCourses().add(course);
        studyService.save(study); // Ensure this persists the changes
        return ResponseEntity.ok(study);
    }


    //================================================================================
    // GET Methods
    //================================================================================

    @GetMapping
    public ResponseEntity<List<Study>> getAllStudies() {
        List<Study> allStudies = studyService.findAll();
        return ResponseEntity.ok(allStudies);
    }

    //================================================================================
    // DELETE Methods
    //================================================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeStudy(@PathVariable Integer id) {
        Optional<Study> findSlug = studyService.findById(id);
        if (findSlug.isPresent()){
            Study found = findSlug.get();
            studyService.delete(found);
            return ResponseEntity.ok(found.getStudyName() + " was removed");
        }
        return ResponseEntity.status(409).body("Bad request, slug not found");
    }

    @DeleteMapping("/{id}/{courseIdentifier}")
    public ResponseEntity<String> removeCourse(@PathVariable Integer id, @PathVariable Integer courseIdentifier) {
        Optional<Study> findSlug = studyService.findById(id);
        if (findSlug.isPresent()){
            Study found = findSlug.get();
            List<Course> courses = found.getCourses();

            Course courseToRemove = courses.stream()
                    .filter(course -> course.getId() == (courseIdentifier))
                    .findFirst()
                    .orElse(null);

            if (courseToRemove != null) {
                courses.remove(courseToRemove);
                studyService.save(found); // Persist the updated study
                return ResponseEntity.ok(courseToRemove.getName() + " was removed from " + found.getStudyName());
            } else {
                return ResponseEntity.status(404).body("Course not found in the study");
            }

        }
        return ResponseEntity.status(409).body("Study not found");
    }

}
