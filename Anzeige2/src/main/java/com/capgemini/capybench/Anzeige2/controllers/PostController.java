package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.NewPostDto;
import com.capgemini.capybench.Anzeige2.dto.PersonDto;
import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.PostService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PersonRepository personRepository;


    @Autowired
    public PostController (PostService postService){
        this.postService = postService;
    }
    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<String> addPost(@RequestBody NewPostDto postDto){

        return ResponseEntity.ok("Post successfully added");
    }
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<PostDto>> getPostsByUser(
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
            final var allPostsFollowedBy = postRepository.findAllPostsFollowedBy(person.getId());
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
        final PersonDto author = PersonDto
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
}