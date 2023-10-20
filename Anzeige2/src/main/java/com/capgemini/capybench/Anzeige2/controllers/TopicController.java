package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/topics")
public class TopicController {
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<Topic>> getAllTopics(){

        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping("/{id}")
    @CrossOrigin("*")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") Long topicId){

        Topic topic = new Topic(topicId,
                                "Hello Pupa",
                                new HashSet<Person>(),
                                new HashSet<Subtopic>(),
                                new HashSet<>());
        return ResponseEntity.ok(topic);
    }
    @PutMapping
    @CrossOrigin("*")
    public ResponseEntity<Long> addSubtopic(@RequestParam("topicId") Long topicId,
                                            @RequestParam("subtopicName") String subtopicName){

        return ResponseEntity.ok(1L);
    }
}
