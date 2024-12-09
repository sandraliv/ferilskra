package io.github.sandraliv.ferilskra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class frontpageController {
    @GetMapping
    public ResponseEntity<String> getFrontPage(){
        return ResponseEntity.ok("Hi welcome to my website");
    }
}
