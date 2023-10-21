package com.capgemini.capybench.Anzeige2.service;

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

    public Long addPost(PostDto postDto, Long topicId, Long subtopicId) {
        Subtopic subtopic = findSubtopicById(subtopicId);
        Topic topic = findTopicById(topicId);
        Post post = addTopicAndSubtopicToPost(postDto, topic, subtopic);
        addPostToSubtopic(subtopic, post);
        addPostToTopic(topic, post);
        return post.getId();
    }

    @Override
    public Long addPost(PostDto postDto, Long topicId) {
        Topic topic = findTopicById(topicId);
        Post post = addTopicToPost(postDto, topic);
        addPostToTopic(topic, post);
        return post.getId();
    }

    @Override
    public List<PostDto> getAllFollowedPostsByPersonId(Long personId) {
        Person person = findPersonById(personId);

        List<Long> topicIds = person.getSubscribedTopics()
                .stream()
                .map(Topic::getId)
                .toList();

        List<Long> subtopicIds = person.getSubscribedSubtopics()
                .stream()
                .map(Subtopic::getId)
                .toList();

        List<Long> postIds = person.getSubscribedPosts()
                .stream()
                .map(Post::getId)
                .toList();

        return postRepository.findAllBy(topicIds, subtopicIds, postIds)
                .stream()
                .map(postMapper::toDto)
                .toList();

//        Set<Post> followedPosts = person.getSubscribedPosts();
//        Set<Post> postsFromFollowedSubtopics = person.getSubscribedSubtopics().stream()
//                .flatMap(subtopic -> subtopic.getPosts().stream())
//                .collect(Collectors.toSet());
//        Set<Post> postsFromFollowedTopics = person.getSubscribedTopics().stream()
//                .flatMap(subtopic -> subtopic.getPosts().stream())
//                .collect(Collectors.toSet());
//        List<Post> allPosts = new ArrayList<>();
//        allPosts.addAll(followedPosts);
//        allPosts.addAll(postsFromFollowedSubtopics);
//        allPosts.addAll(postsFromFollowedTopics);
//        return allPosts.stream()
//                .distinct()
//                .map(postMapper::toDto)
//                .toList();
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
