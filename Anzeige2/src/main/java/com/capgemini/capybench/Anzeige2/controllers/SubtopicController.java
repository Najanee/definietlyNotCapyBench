package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.configuration.RedirectSsl;
import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.service.interfaces.SubtopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subtopics")
@RequiredArgsConstructor
public class SubtopicController {
    @Autowired
    private SubtopicService subtopicService;

    @GetMapping(params = "topicId")
    @CrossOrigin("*")
    @RedirectSsl
    public ResponseEntity<List<SubtopicDto>> getAllSubtopicsByTopic(@RequestParam("topicId") Long topicId){
        List<SubtopicDto> responseBody = subtopicService.getAllSubtopicsByTopicId(topicId);

        return ResponseEntity.ok(responseBody);
    }
    @PutMapping
    @CrossOrigin("*")
    @RedirectSsl
    public ResponseEntity<Long> addSubtopic(@RequestParam("topicId") Long topicId,
                                            @RequestParam("subtopicName") String subtopicName){

        return ResponseEntity.ok(1L);
    }
}