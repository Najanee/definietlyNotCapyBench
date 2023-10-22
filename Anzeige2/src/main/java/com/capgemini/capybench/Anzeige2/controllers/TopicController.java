package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
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
import java.util.Objects;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubtopicRepository subtopicRepository;
    @Autowired
    private TopicService topicService;

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<TopicDto>> getAllTopics() {

        return topicRepository.findAll()
                              .stream()
                              .map(this::composeTopicDto)
                              .collect(collectingAndThen(
                                  toList(),
                                  ResponseEntity::ok));
    }

    private TopicDto composeTopicDto(
        @NonNull final Topic topic) {

        final var topicId = topic.getId();

        final var allPosts = topicRepository
            .findAllPostIdsInTopic(topicId)
            .stream()
            .filter(Objects::nonNull)
            .collect(toSet());

        final var directSubscribers = topicRepository
            .findDirectSubscriberIdsOfTopic(topicId)
            .stream()
            .filter(Objects::nonNull)
            .collect(toSet());

        final var subtopicDtos = subtopicRepository
            .findSubtopicsInTopic(topicId)
            .stream()
            .map(this::composeSubtopicDto)
            .collect(toSet());

        return TopicDto
            .builder()
            .id(topicId)
            .name(topic.getName())
            .subtopics(subtopicDtos)
            .postIds(allPosts)
            .subscriberIds(directSubscribers)
            .expirationDate(topic.getExpirationDate())
            .build();
    }

    private SubtopicDto composeSubtopicDto(
        @NonNull final Subtopic subtopic) {

        final var subtopicId = subtopic.getId();

        final var postIds = subtopicRepository
            .findPostIdsInSubtopic(subtopicId)
            .stream()
            .filter(Objects::nonNull)
            .collect(toSet());

        final var subscriberIds = subtopicRepository
            .findSubscriberIdsOfSubtopic(subtopicId)
            .stream()
            .filter(Objects::nonNull)
            .collect(toSet());

        return SubtopicDto
            .builder()
            .id(subtopicId)
            .name(subtopic.getName())
            .postsIds(postIds)
            .subscriberIds(subscriberIds)
            .expirationDate(subtopic.getExpirationDate())
            .build();
    }

    @GetMapping("/{id}")
    @CrossOrigin("*")
    public ResponseEntity<TopicDto> getTopic(@PathVariable("id") Long topicId) {

        final var topic = topicService.getTopicById(topicId);

        return ResponseEntity.ok(topic);
    }
}