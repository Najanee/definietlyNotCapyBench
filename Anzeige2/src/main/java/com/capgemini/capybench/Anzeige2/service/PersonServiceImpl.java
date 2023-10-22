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

    public static final String ERROR = "ERROR: ";
    public static final String SUBSCRIPTION_TO_POST_REMOVED = "Subscription to Post removed.";
    public static final String SUBSCRIPTION_TO_SUBTOPIC_REMOVED = "Subscription to Subtopic removed.";
    private static final String SUBSCRIPTION_TO_TOPIC_REMOVED = "Subscription to Topic removed.";
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
    public String unsubscribeFromPost(Long postId, Long subscriberId) {
        Person person;
        Set<Post> subscribedPosts;
        Post postToRemove;
        try {
            person = personRepository
                    .findById(subscriberId)
                    .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
            subscribedPosts = person.getSubscribedPosts();
            postToRemove = postRepository
                    .findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(postId)));
        }catch (IllegalArgumentException e){
            return ERROR + e.getMessage();
        }

        subscribedPosts.remove(postToRemove);
        person.setSubscribedPosts(subscribedPosts);
        personRepository.save(person);
        return SUBSCRIPTION_TO_POST_REMOVED;
    }

    @Override
    public String unsubscribeFromSubtopic(Long subtopicId, Long subscriberId) {
        Person person;
        Set<Subtopic> subscribedSubtopics;
        Subtopic subtopicToRemove;
        try{
            person = personRepository
                    .findById(subscriberId)
                    .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
            subscribedSubtopics = person.getSubscribedSubtopics();
            subtopicToRemove = subtopicRepository
                    .findById(subtopicId)
                    .orElseThrow(() -> new IllegalArgumentException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subtopicId)));
        }catch (IllegalArgumentException e){
            return ERROR + e.getMessage();
        }
        subscribedSubtopics.remove(subtopicToRemove);
        person.setSubscribedSubtopics(subscribedSubtopics);
        personRepository.save(person);
        return SUBSCRIPTION_TO_SUBTOPIC_REMOVED;
    }

    @Override
    public String unsubscribeFromTopic(Long topicId, Long subscriberId) {
        Person person;
        Set<Topic> subscribedTopics;
        Topic topicToRemove;
        try {
            person = personRepository
                    .findById(subscriberId)
                    .orElseThrow(() -> new IllegalArgumentException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(subscriberId)));
            subscribedTopics = person.getSubscribedTopics();
            topicToRemove = topicRepository
                    .findById(topicId)
                    .orElseThrow(() -> new IllegalArgumentException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));
        }catch (IllegalArgumentException e){
            return ERROR + e.getMessage();
        }
        subscribedTopics.remove(topicToRemove);
        person.setSubscribedTopics(subscribedTopics);
        personRepository.save(person);
        return SUBSCRIPTION_TO_TOPIC_REMOVED;
    }
}
