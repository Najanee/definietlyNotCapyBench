package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController (PostService postService){
        this.postService = postService;
    }
    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto){

        return ResponseEntity.ok("Post successfully added");
    }
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<PostDto>> getPostsByUser(@RequestParam("userId") Long userId){

        return ResponseEntity.ok(postService.getAllFollowedPostsByPersonId(userId));
    }
}
