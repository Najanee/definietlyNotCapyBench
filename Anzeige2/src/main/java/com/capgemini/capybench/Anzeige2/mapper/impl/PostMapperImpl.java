package com.capgemini.capybench.Anzeige2.mapper.impl;

import com.capgemini.capybench.Anzeige2.dto.PersonDto;
import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.mapper.PostMapper;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;
@Component
public class PostMapperImpl implements PostMapper {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final  TopicRepository topicRepository;
    @Autowired
    private final SubtopicRepository subtopicRepository;

    public PostMapperImpl(PersonRepository personRepository, TopicRepository topicRepository, SubtopicRepository subtopicRepository) {
        this.personRepository = personRepository;
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
    }

    @Override
    public PostDto toDto(Post entity) {
        if (entity == null) {
            throw new IllegalArgumentException(POST_MUST_NOT_BE_NULL);
        }
        return PostDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createDate(entity.getCreatedDate())
                .author(PersonDto.builder()
                        .id(entity.getAuthor().getId())
                        .name(entity.getAuthor().getName())
                        .imageUrl(entity.getAuthor().getImageUrl())
                        .build())
                .expirationDate(entity.getExpirationDate())
                .topicId(entity.getTopic().getId())
                .subtopicId(entity.getSubtopic().getId())
                .subscriberIds(entity.getPeople().stream()
                        .map(Person::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Post toEntity(PostDto dto) {
        if (dto == null) {throw new IllegalArgumentException(POST_DTO_MUST_NOT_BE_NULL);}
        return Post.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDate(dto.getCreateDate())
                .expirationDate(dto.getExpirationDate())
                .author(personRepository
                        .findById(dto.getAuthor().getId())
                        .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getAuthor().getId()))))
                .topic(topicRepository
                        .findById(dto.getTopicId())
                        .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getTopicId()))))
                .subtopic(subtopicRepository
                        .findById(dto.getSubtopicId())
                        .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getSubtopicId()))))
                .people(dto.getSubscriberIds().stream()
                        .map(id -> personRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id))))
                                .collect(Collectors.toSet()))
                .build();

    }
}
