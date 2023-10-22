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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<String> addPost(@RequestBody NewPostDto postDto){
        postService.addPost(postDto);
        return ResponseEntity.ok("Post successfully added");
    }
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<PostDto>> getPostsBySubscriber(
        @RequestParam("subscriberId") Long subscriberId){

        final var subscriberToFollowedPosts = getSubscribersToFollowedPosts();

        final var postToSubscribers = getPostToSubscribers(subscriberToFollowedPosts);

        final var followedPosts = getPostsBySubscriber(subscriberToFollowedPosts, subscriberId);

        return followedPosts
            .stream()
            .map(post -> this.mapToPostDto(post, postToSubscribers.get(post)))
            .collect(collectingAndThen(
                toList(),
                ResponseEntity::ok));
    }

    private Map<Person, List<Post>> getSubscribersToFollowedPosts() {

        final var people = personRepository.findAll();

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

    private static List<Post> getPostsBySubscriber(
        @NonNull final Map<Person, List<Post>> personToFollowedPosts,
        @NonNull final Long subscriberId) {
        return personToFollowedPosts
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey().getId().equals(subscriberId))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(new ArrayList<>());
    }

    private PostDto mapToPostDto(
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
}