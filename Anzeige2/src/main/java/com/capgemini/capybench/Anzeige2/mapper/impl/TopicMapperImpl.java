package com.capgemini.capybench.Anzeige2.mapper.impl;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.mapper.SubtopicMapper;
import com.capgemini.capybench.Anzeige2.mapper.TopicMapper;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

@Component
@AllArgsConstructor
public class TopicMapperImpl implements TopicMapper {

    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final SubtopicRepository subtopicRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final SubtopicMapper subtopicMapper;

    @Override
    public TopicDto toDto(Topic entity) {
        if (entity == null) {
            throw new IllegalArgumentException(TOPIC_MUST_NOT_BE_NULL);
        }
        List<Subtopic> subtopics = subtopicRepository.findAllSubtopicsByTopicId(entity.getId());
        Set<Long> subscribersToTopic = entity.getPeople().stream().map(person -> person.getId()).collect(Collectors.toSet());
        Set<SubtopicDto> subtopicDtos = mapToSubtopicDtos(subtopics);
//        Set<Long> subscribersIds = personRepository.findAllPeopleByTopicId(entity.getId()).stream()
//                .map(Person::getId)
//                .collect(Collectors.toSet());
        return TopicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .expirationDate(entity.getExpirationDate())
                .subscriberIds(subscribersToTopic)
                .subtopics(subtopicDtos)
                .build();
    }

    private Set<SubtopicDto> mapToSubtopicDtos(List<Subtopic> subtopics) {
        return subtopics.stream()
                .map(subtopicMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Topic toEntity(TopicDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(TOPIC_DTO_MUST_NOT_BE_NULL);
        }
        return Topic.builder()
                .id(dto.getId())
                .name(dto.getName())
                .expirationDate(dto.getExpirationDate())
                .people(dto.getSubscriberIds().stream()
                        .map(id -> personRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .subtopics(dto.getSubtopics().stream()
                        .map(s -> subtopicRepository
                                .findById(s.getId())
                                .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(s.getId()))))
                        .collect(Collectors.toSet()))
                .build();
    }
}
