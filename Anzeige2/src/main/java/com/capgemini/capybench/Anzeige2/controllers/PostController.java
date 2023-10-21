package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.NewPostDto;
import com.capgemini.capybench.Anzeige2.dto.PersonDto;
import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<PostDto>> getPostsByUser(@RequestParam("userId") Long userId){

//        final var allFollowedPostsByPersonId = postService.getAllFollowedPostsByPersonId(userId);

        final var allFollowedPostsByPersonId = postRepository.findAllPostsFollowedBy(userId)
                                                             .stream()
                                                             .peek(p -> log.info(p.toString()))
                                                             .map(this::map)
                                                             .toList();

        return ResponseEntity.ok(allFollowedPostsByPersonId);
    }

    private PostDto map(Post post) {
        final PersonDto author = PersonDto
            .builder()
            .id(post.getAuthor().getId())
            .name(post.getAuthor().getName())
            .imageUrl(post.getAuthor().getImageUrl())
            .build();

        final var subscriberIds = post
            .getSubscribers()
            .stream()
            .map(Person::getId)
            .collect(Collectors.toSet());

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