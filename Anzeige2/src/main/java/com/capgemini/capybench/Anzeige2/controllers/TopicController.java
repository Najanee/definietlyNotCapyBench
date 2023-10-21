package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController (TopicService topicService){
        this.topicService = topicService;
    }
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<TopicDto>> getAllTopics(){
        return ResponseEntity.ok(topicService.getAllTopics());
    }
    @GetMapping("/{id}")
    @CrossOrigin("*")
    public ResponseEntity<TopicDto> getTopic(@PathVariable("id") Long topicId){

        return ResponseEntity.ok(topicService.getTopicById(topicId));
    }

}
