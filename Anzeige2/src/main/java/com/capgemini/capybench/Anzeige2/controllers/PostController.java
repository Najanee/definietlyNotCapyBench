package com.capgemini.capybench.Anzeige2.controllers;

import com.capgemini.capybench.Anzeige2.dto.PostRequestDto;
import com.capgemini.capybench.Anzeige2.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<String> addPost(@RequestBody PostRequestDto postDto){

        return ResponseEntity.ok("Post successfully added");
    }
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<Post>> getPostsByUser(@RequestParam("userId") Long userId){

        return ResponseEntity.ok(new ArrayList<>());
    }
}
