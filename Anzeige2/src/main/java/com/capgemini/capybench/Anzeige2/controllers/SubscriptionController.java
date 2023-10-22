package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.service.interfaces.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private PersonService personService;

    @PatchMapping(path = "/{subscriberId}", params = "postId")
    @CrossOrigin("*")
    public ResponseEntity<String> subscribeToPost(
        @RequestParam("postId") Long postId,
        @PathVariable("subscriberId") Long subscriberId) {

        personService.addFollowedPost(postId, subscriberId);

        return ResponseEntity.ok("Subscription to Post added.");
    }

    @PatchMapping(path = "/{subscriberId}", params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> subscribeToSubtopic(
        @RequestParam("subtopicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {

        personService.addFollowedSubtopic(subtopicId, subscriberId);

        return ResponseEntity.ok("Subscription to subtopic added.");
    }

    @PatchMapping(path = "/{subscriberId}", params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<String> subscribeToTopic(
        @RequestParam("topicId") Long topicId,
        @PathVariable("subscriberId") Long subscriberId) {

        personService.addFollowedTopic(topicId, subscriberId);

        return ResponseEntity.ok("Subscription to Topic added.");
    }

    @DeleteMapping(path = "/{subscriberId}", params = "postId")
    @CrossOrigin("*")
    public ResponseEntity<String> unsubscribeFromPost(
        @RequestParam("postId") Long postId,
        @PathVariable("subscriberId") Long subscriberId) {

        return ResponseEntity.ok("Subscription to Post removed.");
    }

    @DeleteMapping(path = "/{subscriberId}", params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> unsubscribeFromSubtopic(
        @RequestParam("subtopicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {

        return ResponseEntity.ok("Subscription to Subtopic removed.");
    }

    @DeleteMapping(path = "/{subscriberId}", params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<String> unsubscribeFromTopic(
        @RequestParam("topicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {

        return ResponseEntity.ok("Subscription to Topic removed.");
    }
}