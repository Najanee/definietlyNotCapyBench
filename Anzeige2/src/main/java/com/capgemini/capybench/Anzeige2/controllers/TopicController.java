package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/topic")
public class TopicController {

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") int topicId){
        
        return ResponseEntity.ok(new Topic());
    }
}
