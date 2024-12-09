package io.github.sandraliv.ferilskra.controllers;

import io.github.sandraliv.ferilskra.klasar.Study;
import io.github.sandraliv.ferilskra.services.studyServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addStudy")
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

}
