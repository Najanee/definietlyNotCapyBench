package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subtopics")
public class SubtopicController {

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<Subtopic>> getAllSubtopics(){

        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping(params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<List<Subtopic>> getAllSubtopicsByTopic(@RequestParam("topicId") Long topicId){

        return ResponseEntity.ok(new ArrayList<>());
    }

}
