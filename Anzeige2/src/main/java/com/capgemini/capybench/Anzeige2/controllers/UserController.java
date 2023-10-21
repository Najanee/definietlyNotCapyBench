package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.service.interfaces.PersonService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("users")
public class UserController {

    private PersonService personService;

    @Autowired
    public UserController(PersonService personService){
        this.personService = personService;
    }
    @PatchMapping(path = "/{userId}",
                  params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> addFollowedSubtopic(@RequestParam("subtopicId") Long subtopicId,
                                                      @PathVariable("userId") Long userId) {
        personService.addFollowedSubtopic(subtopicId, userId);
        return ResponseEntity.ok("Followed subtopic added");
    }
    @PatchMapping(path = "/{userId}",
                  params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<String> addFollowedTopic(@RequestParam("topicId") Long topicId,
                                                      @PathVariable("userId") Long userId) {
        personService.addFollowedTopic(topicId, userId);
        return ResponseEntity.ok("Topic added");
    }
    @PatchMapping(path = "/{userId}",
                  params = "postId")
    @CrossOrigin("*")
    public ResponseEntity<String> addFollowedPost(@RequestParam("postId") Long postId,
                                                   @PathVariable("userId") Long userId) {
        personService.addFollowedPost(postId, userId);
        return ResponseEntity.ok("Followed post added");
    }
    @DeleteMapping(path = "/{userId}",
                   params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> removeFollowedSubtopic(@RequestParam("subtopicId") Long subtopicId,
                                                      @PathVariable("userId") Long userId) {
        return ResponseEntity.ok("Subtopic added");
    }
    @DeleteMapping(path = "/{userId}",
                   params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<String> removeFollowedTopic(@RequestParam("topicId") Long subtopicId,
                                                   @PathVariable("userId") Long userId) {

        return ResponseEntity.ok("Topic added");
    }
}
