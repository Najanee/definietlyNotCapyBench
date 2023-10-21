package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.PostMapper;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND;
import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.TOPIC_ENTITY_WITH_ID_S_NOT_FOUND;

@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final PostMapper postMapper;
    @Autowired
    private final TopicRepository topicRepository;
    @Autowired
    private final SubtopicRepository subtopicRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, TopicRepository topicRepository, SubtopicRepository subtopicRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
    }

    public Long addPost(PostDto postDto, Long topicId, Long subtopicId) {
        Subtopic subtopic = subtopicRepository
                .findById(subtopicId)
                .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subtopicId)));
        Topic topic = topicRepository
                .findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));

        Post post = addTopicAndSubtopicToPost(postDto, topic, subtopic);
        addPostToSubtopic(subtopic, post);
        addPostToTopic(topic, post);

        return post.getId();
    }

    //BYLES KURWA W WOJSKU?
    @Override
    public Long addPost(PostDto postDto, Long topicId) {
        return null;
    }

    @Override
    public List<PostDto> getAllFollowedPostsByPersonId(Long personId) {
        return null;
    }

    private Post addTopicAndSubtopicToPost(PostDto postDto, Topic topic, Subtopic subtopic) {
        Post post = postMapper.toEntity(postDto);
        post.setTopic(topic);
        post.setSubtopic(subtopic);
        postRepository.save(post);
        return post;
    }

    private void addPostToTopic(Topic topic, Post post) {
        Set<Post> topicPosts = topic.getPosts();
        topicPosts.add(post);
        topicRepository.save(topic);
    }

    private void addPostToSubtopic(Subtopic subtopic, Post post) {
        Set<Post> subtopicPosts = subtopic.getPosts();
        subtopicPosts.add(post);
        subtopicRepository.save(subtopic);
    }
}
