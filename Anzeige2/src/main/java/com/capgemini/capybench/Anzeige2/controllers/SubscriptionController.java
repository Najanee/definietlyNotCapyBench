package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.service.interfaces.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.capgemini.capybench.Anzeige2.service.PersonServiceImpl.ERROR;

@RestController
@CrossOrigin("*")
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private PersonService personService;

    @PostMapping(path = "/{subscriberId}", params = "postId")
    @CrossOrigin("*")
    public ResponseEntity<String> subscribeToPost(
        @RequestParam("postId") Long postId,
        @PathVariable("subscriberId") Long subscriberId) {

        personService.addFollowedPost(postId, subscriberId);

        return ResponseEntity.ok("Subscription to Post added.");
    }

    @PostMapping(path = "/{subscriberId}", params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> subscribeToSubtopic(
        @RequestParam("subtopicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {

        personService.addFollowedSubtopic(subtopicId, subscriberId);

        return ResponseEntity.ok("Subscription to subtopic added.");
    }

    @PostMapping(path = "/{subscriberId}", params = "topicId")
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
        String response = personService.unsubscribeFromPost(postId, subscriberId);
        if (response.startsWith(ERROR)){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{subscriberId}", params = "subtopicId")
    @CrossOrigin("*")
    public ResponseEntity<String> unsubscribeFromSubtopic(
        @RequestParam("subtopicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {
        String response = personService.unsubscribeFromSubtopic(subtopicId, subscriberId);
        if (response.startsWith(ERROR)){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{subscriberId}", params = "topicId")
    @CrossOrigin("*")
    public ResponseEntity<String> unsubscribeFromTopic(
        @RequestParam("topicId") Long subtopicId,
        @PathVariable("subscriberId") Long subscriberId) {
        String response = personService.unsubscribeFromTopic(subtopicId, subscriberId);
        if (response.startsWith(ERROR)){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}