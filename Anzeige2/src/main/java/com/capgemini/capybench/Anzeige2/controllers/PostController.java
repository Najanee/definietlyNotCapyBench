package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.NewPost;
import com.capgemini.capybench.Anzeige2.entity.Post;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody NewPost newPost){

        return ResponseEntity.ok("Post successfully added");
    }
    @GetMapping
    public ResponseEntity<List<Post>> getPostsByUser(@RequestParam("userId") long userId){

        return ResponseEntity.ok(new ArrayList<>());
    }
}
