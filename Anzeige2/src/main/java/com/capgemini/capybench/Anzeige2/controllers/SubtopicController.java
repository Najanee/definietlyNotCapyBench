package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.service.interfaces.SubtopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subtopics")
public class SubtopicController {
    private SubtopicService subtopicService;

    @Autowired
    public SubtopicController(SubtopicService subtopicService){
        this.subtopicService = subtopicService;
    }

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
