package com.capgemini.capybench.Anzeige2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @PatchMapping(path = "/{userId}",
                  params = "subtopicId")
    public ResponseEntity<String> addFollowedSubtopic(@RequestParam("subtopicId") Long subtopicId,
                                                      @PathVariable("userId") Long userId) {

        return ResponseEntity.ok("Subtopic added");
    }
    @PatchMapping(path = "/{userId}",
                  params = "topicId")
    public ResponseEntity<String> addFollowedTopic(@RequestParam("topicId") Long subtopicId,
                                                      @PathVariable("userId") Long userId) {

        return ResponseEntity.ok("Topic added");
    }
    @DeleteMapping(path = "/{userId}",
                   params = "subtopicId")
    public ResponseEntity<String> removeFollowedSubtopic(@RequestParam("subtopicId") Long subtopicId,
                                                      @PathVariable("userId") Long userId) {

        return ResponseEntity.ok("Subtopic added");
    }
    @DeleteMapping(path = "/{userId}",
                   params = "topicId")
    public ResponseEntity<String> removeFollowedTopic(@RequestParam("topicId") Long subtopicId,
                                                   @PathVariable("userId") Long userId) {

        return ResponseEntity.ok("Topic added");
    }
}
