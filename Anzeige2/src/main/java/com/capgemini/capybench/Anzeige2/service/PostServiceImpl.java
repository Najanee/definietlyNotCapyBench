package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.NewPostDto;
import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.PostMapper;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;
@Component
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
    @Autowired
    private final PersonRepository personRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, TopicRepository topicRepository, SubtopicRepository subtopicRepository, PersonRepository personRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
        this.personRepository = personRepository;
    }
    @Override
    public PostDto addPost(NewPostDto newPostDto) {
        Subtopic subtopic = findSubtopicById(newPostDto.getSubtopicId());
        Topic topic = findTopicById(newPostDto.getTopicId());
        Person author = findPersonById(newPostDto.getAuthorId());
        Post newPost = new Post();
        newPost.setContent(newPostDto.getContent());
        newPost.setTitle(newPostDto.getTitle());
        newPost.setAuthor(author);
        newPost.setSubtopic(subtopic);
        newPost.setTopic(topic);
        newPost.setPeople(new HashSet<>());
        Post persistedPost = postRepository.save(newPost);

        Set<Post> subscribedPosts = author.getSubscribedPosts();
        subscribedPosts.add(persistedPost);
        author.setSubscribedPosts(subscribedPosts);
        personRepository.save(author);

        return postMapper.toDto(persistedPost);
    }

    @Override
    public List<PostDto> getAllFollowedPostsByPersonId(Long personId) {
        return null;
    }

    private Person findPersonById(Long personId) {
        return personRepository
                .findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(personId)));
    }

    private Topic findTopicById(Long topicId) {
        return topicRepository
                .findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));
    }

    private Subtopic findSubtopicById(Long subtopicId) {
        if (subtopicId == null) {
            return null;
        }
        return subtopicRepository
                .findById(subtopicId)
                .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subtopicId)));
    }

    private Post addTopicAndSubtopicToPost(PostDto postDto, Topic topic, Subtopic subtopic) {
        Post post = postMapper.toEntity(postDto);
        post.setTopic(topic);
        post.setSubtopic(subtopic);
        postRepository.save(post);
        return post;
    }

    private Post addTopicToPost(PostDto postDto, Topic topic) {
        Post post = postMapper.toEntity(postDto);
        post.setTopic(topic);
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
