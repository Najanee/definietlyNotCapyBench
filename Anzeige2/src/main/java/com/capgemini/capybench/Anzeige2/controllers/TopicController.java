package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") long topicId){

        Topic topic = new Topic(
                topicId,
                "Hello Dupa",
                new HashSet<Person>(),
                new HashSet<Subtopic>(),
                new HashSet<Post>());
        return ResponseEntity.ok(topic);
    }
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics(){

        return ResponseEntity.ok(new ArrayList<>());
    }
}
