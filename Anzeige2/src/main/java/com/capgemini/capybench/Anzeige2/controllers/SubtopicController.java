package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.service.SubtopicServiceImpl;
import com.capgemini.capybench.Anzeige2.service.interfaces.SubtopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subtopics")
public class SubtopicController {
    private SubtopicService subtopicService;

    @Autowired
    public SubtopicController(SubtopicServiceImpl subtopicService){
        this.subtopicService = subtopicService;
    }

    @GetMapping(params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<List<SubtopicDto>> getAllSubtopicsByTopic(@RequestParam("topicId") Long topicId){
        List<SubtopicDto> responseBody = subtopicService.getAllSubtopicsByTopicId(topicId);

        return ResponseEntity.ok(responseBody);
    }
    @PutMapping
    @CrossOrigin("*")
    public ResponseEntity<Long> addSubtopic(@RequestParam("topicId") Long topicId,
                                            @RequestParam("subtopicName") String subtopicName){
        return ResponseEntity.ok(1L);
    }
}
