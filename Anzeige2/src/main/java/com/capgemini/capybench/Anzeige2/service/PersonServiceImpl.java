package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.PersonDto;
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

@Component
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private TopicRepository topicRepository;
    private SubtopicRepository subtopicRepository;
    private PostRepository postRepository;

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

}
