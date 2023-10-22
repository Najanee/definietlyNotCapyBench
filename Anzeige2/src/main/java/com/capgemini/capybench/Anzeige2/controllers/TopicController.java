package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.TopicService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubtopicRepository subtopicRepository;
    @Autowired
    private PersonRepository personRepository;

//    @GetMapping
//    @CrossOrigin("*")
//    public ResponseEntity<List<TopicDto>> getAllTopics(){
//        final var topics = topicRepository.findAll();
//
//        final var subtopics = subtopicRepository.findAllEagerly();
//
//        final var subtopicDtos = subtopics
//            .stream()
//            .map(subtopic -> this.map(subtopic))
//            .toList();
//
//        final var people = personRepository.findAll();
//
//        return topics
//            .stream()
//            .map(topic -> this.map(topic))
//            .collect(Collectors.collectingAndThen(
//                toList(),
//                ResponseEntity::ok));
//    }
//
//    private TopicDto map(
//        @NonNull final Topic topic) {
//
//        return TopicDto
//            .builder()
//            .id(topic.getId())
//            .name(topic.getName())
//            .subtopics(null)
//            .subscriberIds(null)
//            .expirationDate(topic.getExpirationDate())
//            .build();
//    }
//
//    private SubtopicDto map(
//        @NonNull final Subtopic subtopic,
//        @NonNull final Set<Long> subscribersIds) {
//
//        return SubtopicDto
//            .builder()
//            .id(subtopic.getId())
//            .name(subtopic.getName())
//            .subscriberIds(subscribersIds)
//            .expirationDate(subtopic.getExpirationDate())
//            .build();
//    }
//
//    @GetMapping("/{id}")
//    @CrossOrigin("*")
//    public ResponseEntity<TopicDto> getTopic(@PathVariable("id") Long topicId){
//
//        return ResponseEntity.ok(topicService.getTopicById(topicId));
//    }
}