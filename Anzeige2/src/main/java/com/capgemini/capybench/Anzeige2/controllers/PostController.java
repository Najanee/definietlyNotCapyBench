package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.*;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubtopicRepository subtopicRepository;

    @PostMapping
    @CrossOrigin("*")
//    @RequestMapping("/posts")
    public ResponseEntity<String> addPost(@RequestBody NewPostDto postDto){

        return ResponseEntity.ok("Post successfully added");
    }

    @GetMapping
    @CrossOrigin("*")
    @RequestMapping("/topics")
    public ResponseEntity<List<TopicDto>> getAllTopics() {

        return topicRepository.findAll()
                              .stream()
                              .map(this::map)
                              .collect(collectingAndThen(
                                  toList(),
                                  ResponseEntity::ok));
    }

    @GetMapping
    @CrossOrigin("*")
    @RequestMapping("/posts")
    public ResponseEntity<List<PostDto>> getPostsBySubscriber(
        @RequestParam("subscriberId") Long subscriberId){

        final var people = personRepository.findAll();

        final var subscriberToFollowedPosts = getSubscribersToFollowedPosts(people);

        final var postToSubscribers = getPostToSubscribers(subscriberToFollowedPosts);

        final var followedPosts = findAllPostsBySubscriber(subscriberId, subscriberToFollowedPosts);

        return followedPosts
            .stream()
            .map(post -> this.map(post, postToSubscribers.get(post)))
            .collect(collectingAndThen(
                toList(),
                ResponseEntity::ok));
    }

    private Map<Person, List<Post>> getSubscribersToFollowedPosts(
       @NonNull final List<Person> people) {

        Map<Person, List<Post>> personToFollowedPosts = new HashMap<>();

        people.forEach(person -> {
            final var allPostsFollowedBy = postRepository.findPostsForPerson(person.getId());
            personToFollowedPosts.put(person, allPostsFollowedBy);
        });
        return personToFollowedPosts;
    }

    private static Map<Post, List<Person>> getPostToSubscribers(
        @NonNull final Map<Person, List<Post>> personToFollowedPosts) {
        Map<Post, List<Person>> postToSubscribers = new HashMap<>();

        for (Map.Entry<Person, List<Post>> entry : personToFollowedPosts.entrySet()) {
            Person person = entry.getKey();
            List<Post> followedPosts = entry.getValue();

            for (Post post1 : followedPosts) {
                // Check if the post is already in postToSubscribers; if not, add it.
                postToSubscribers.putIfAbsent(post1, new ArrayList<>());

                // Add the person to the subscribers list for this post.
                postToSubscribers
                    .get(post1).add(person);
            }
        }
        return postToSubscribers;
    }

    private static List<Post> findAllPostsBySubscriber(
        @NonNull final Long userId,
        @NonNull final Map<Person, List<Post>> personToFollowedPosts) {
        return personToFollowedPosts
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey().getId().equals(userId))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(new ArrayList<>());
    }

    private PostDto map(
        @NonNull final Post post,
        @NonNull final List<Person> subscribers) {

        final var author = PersonDto
            .builder()
            .id(post.getAuthor().getId())
            .name(post.getAuthor().getName())
            .imageUrl(post.getAuthor().getImageUrl())
            .build();

        final var subscriberIds = subscribers
            .stream()
            .map(Person::getId)
            .collect(toSet());

        return PostDto
            .builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .createDate(post.getCreatedDate())
            .author(author)
            .expirationDate(post.getExpirationDate())
            .subscriberIds(subscriberIds)
            .topicId(post.getTopic().getId())
            .subtopicId(post.getSubtopic() != null ? post.getSubtopic().getId() : null)
            .build();
    }

    private TopicDto map(
        @NonNull final Topic topic) {

        final var topicId = topic.getId();
        final var allPosts = topicRepository.findAllPostIdsInTopic(topicId)
                                            .stream()
                                            .filter(Objects::nonNull)
                                            .collect(toSet());
        final var directSubscribers = topicRepository.findDirectSubscriberIdsOfTopic(topicId)
                                                     .stream()
                                                     .filter(Objects::nonNull)
                                                     .collect(toSet());
        final var subtopicDtos = subtopicRepository.findSubtopicsInTopic(topicId)
                                                   .stream()
                                                   .map(this::map)
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

    private SubtopicDto map(
        @NonNull final Subtopic subtopic) {

        final var subtopicId = subtopic.getId();
        final var postIds = subtopicRepository.findPostIdsInSubtopic(subtopicId)
                                              .stream()
                                              .filter(Objects::nonNull)
                                              .collect(toSet());
        final var subscriberIds = subtopicRepository.findSubscriberIdsOfSubtopic(subtopicId)
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
}