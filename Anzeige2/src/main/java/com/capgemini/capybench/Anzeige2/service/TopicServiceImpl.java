package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.TopicMapper;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.TopicService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.TOPIC_DTO_MUST_NOT_BE_NULL;
import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.TOPIC_ENTITY_WITH_ID_S_NOT_FOUND;

@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private final TopicRepository topicRepository;
    @Autowired
    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
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
        //TODO: to jest spierdololo
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
}

