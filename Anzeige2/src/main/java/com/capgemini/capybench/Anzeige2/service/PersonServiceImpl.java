package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

@Component
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final TopicRepository topicRepository;
    private final SubtopicRepository subtopicRepository;
    private final PostRepository postRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             TopicRepository topicRepository,
                             SubtopicRepository subtopicRepository,
                             PostRepository postRepository){
        this.personRepository = personRepository;
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
        this.postRepository = postRepository;
    }
    @Override
    public Person addFollowedTopic(Long topicId, Long personId){
        Person person = personRepository.findPersonById(personId);
        Topic topic = topicRepository.getTopicById(topicId);
        person.addTopic(topic);
        personRepository.save(person);
        return person;
    }

    @Override
    public Person addFollowedSubtopic(final Long subtopicId, final Long personId) {
        Person person = personRepository.findPersonById(personId);
        Subtopic subtopic = subtopicRepository.getSubtopicById(subtopicId);
        person.addSubtopic(subtopic);
        personRepository.save(person);
        return person;
    }

    @Override
    public Person addFollowedPost(final Long postId, final Long personId) {
        Person person = personRepository.findPersonById(personId);
        Post post = postRepository.getPostById(postId);
        person.addPost(post);
        personRepository.save(person);
        return person;
    }

    @Override
    public void unsubscribeFromPost(Long postId, Long subscriberId) {
        Person person = personRepository
                .findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
        Set<Post> subscribedPosts = person.getSubscribedPosts();
        Post postToRemove = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(postId)));

        subscribedPosts.remove(postToRemove);
        person.setSubscribedPosts(subscribedPosts);
        personRepository.save(person);
    }

    @Override
    public void unsubscribeFromSubtopic(Long subtopicId, Long subscriberId) {
        Person person = personRepository
                .findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
        Set<Subtopic> subscribedSubtopics = person.getSubscribedSubtopics();
        Subtopic subtopicToRemove = subtopicRepository
                .findById(subtopicId)
                .orElseThrow(() -> new IllegalArgumentException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subtopicId)));

        subscribedSubtopics.remove(subtopicToRemove);
        person.setSubscribedSubtopics(subscribedSubtopics);
        personRepository.save(person);
    }

    @Override
    public void unsubscribeFromTopic(Long topicId, Long subscriberId) {
        Person person = personRepository
                .findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
        Set<Topic> subscribedTopics = person.getSubscribedTopics();
        Topic topicToRemove = topicRepository
                .findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));

        subscribedTopics.remove(topicToRemove);
        person.setSubscribedTopics(subscribedTopics);
        personRepository.save(person);
    }
}
