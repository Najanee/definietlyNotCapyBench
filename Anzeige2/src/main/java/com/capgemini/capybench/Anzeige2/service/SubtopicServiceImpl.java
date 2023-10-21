package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.SubtopicMapper;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.SubtopicService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.TOPIC_ENTITY_WITH_ID_S_NOT_FOUND;

@Service
@Transactional
public class SubtopicServiceImpl implements SubtopicService {

    @Autowired
    private final SubtopicRepository subtopicRepository;
    @Autowired
    private final SubtopicMapper subtopicMapper;
    @Autowired
    private final TopicRepository topicRepository;

    public SubtopicServiceImpl(SubtopicRepository subtopicRepository, SubtopicMapper subtopicMapper, TopicRepository topicRepository) {
        this.subtopicRepository = subtopicRepository;
        this.subtopicMapper = subtopicMapper;
        this.topicRepository = topicRepository;
    }


    @Override
    public Long addSubtopic(SubtopicDto subtopicDto, Long topicId) {
        Topic topic = topicRepository
                .findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));

        Subtopic subtopicEntity = subtopicMapper.toEntity(subtopicDto);
        subtopicEntity = subtopicRepository.save(subtopicEntity);

        Set<Subtopic> subtopics = topic.getSubtopics();
        subtopics.add(subtopicEntity);
        topic.setSubtopics(subtopics);
        topicRepository.save(topic);

        return subtopicEntity.getId();
    }

    @Override
    public List<SubtopicDto> getAllSubtopicsByTopicId(Long topicId) {
        Topic topic = topicRepository
                .findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(topicId)));

        return subtopicRepository.findAllSubtopicsByTopicId(topicId).stream()
                .map(subtopicMapper::toDto)
                .toList();
    }
}
