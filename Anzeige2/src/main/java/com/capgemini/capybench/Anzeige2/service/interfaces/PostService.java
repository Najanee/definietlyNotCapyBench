package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.PostDto;

import java.util.List;

public interface PostService {

    Long addPost(PostDto postDto, Long topicId, Long subtopicId);
    Long addPost(PostDto postDto,Long topicId);
    List<PostDto> getAllFollowedPostsByPersonId(Long personId);
}
