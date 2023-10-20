package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subtopics")
public class SubtopicController {

    @GetMapping
    public ResponseEntity<List<Subtopic>> getAllSubtopics(){

        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping(params = "topicId")
    public ResponseEntity<List<Subtopic>> getAllSubtopicsByTopic(@RequestParam("topicId") long topicId){

        return ResponseEntity.ok(new ArrayList<>());
    }

}
