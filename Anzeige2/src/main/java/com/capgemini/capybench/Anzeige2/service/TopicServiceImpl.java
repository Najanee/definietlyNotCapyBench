package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.TopicMapper;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.TopicService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

@Component
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private final TopicRepository topicRepository;
    @Autowired
    private final TopicMapper topicMapper;
    @Autowired
    private final PersonRepository personRepository;

    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper, PersonRepository personRepository) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
        this.personRepository = personRepository;
    }

    @Override
    public Long addTopic(TopicDto topic) {
        if (topic == null) {
            throw new IllegalArgumentException(TOPIC_DTO_MUST_NOT_BE_NULL);
        }
        Topic topicEntity = topicMapper.toEntity(topic);
        return topicRepository.save(topicEntity).getId();
    }

    @Override
    public void deleteTopic(Long id) {
        Topic topic = topicRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id)));
        topicRepository.delete(topic);
    }

    @Override
    public Long updateTopic(TopicDto topicDto) {
        Topic topicEntity = topicRepository.findById(topicDto.getId()).orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicDto.getId())));
        Topic updatedEntity = topicMapper.toEntity(topicDto);
        if (topicDto.getName()!= null) {
            topicEntity.setName(topicDto.getName());
        }

        return topicRepository.save(topicEntity).getId();
    }

    @Override
    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toDto)
                .toList();
    }

    @Override
    public TopicDto findTopicById(final Long topicId) {
        Topic topic = topicRepository
            .findById(topicId)
            .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));
        return topicMapper.toDto(topic);
    }

    @Override
    public void addFollowedTopic(Long personId, Long topicId) {
        Person person = personRepository
                .findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(personId)));
        Topic topic = topicRepository
                .findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));

        topic.getPeople().add(person);
        person.getSubscribedTopics().add(topic);

        topicRepository.save(topic);
        personRepository.save(person);
    }
}

