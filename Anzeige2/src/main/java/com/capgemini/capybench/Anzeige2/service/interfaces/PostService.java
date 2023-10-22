package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.NewPostDto;
import com.capgemini.capybench.Anzeige2.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto addPost(NewPostDto newPostDto);
    List<PostDto> getAllFollowedPostsByPersonId(Long personId);

}
