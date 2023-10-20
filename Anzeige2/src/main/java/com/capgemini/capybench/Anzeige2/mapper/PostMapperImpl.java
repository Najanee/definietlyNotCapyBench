package com.capgemini.capybench.Anzeige2.mapper;

import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

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
                .title(entity.getTitle())
                .content(entity.getContent())
                .personId(entity.getPerson().getId())
                .topicId(entity.getTopic().getId())
                .subtopicId(entity.getSubtopic().getId())
                .build();
    }

    @Override
    public Post toEntity(PostDto dto) {
        if (dto == null) {throw new IllegalArgumentException(POST_DTO_MUST_NOT_BE_NULL);}
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .person(personRepository
                        .findById(dto.getPersonId())
                        .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getPersonId()))))
                .topic(topicRepository
                        .findById(dto.getTopicId())
                        .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getTopicId()))))
                .subtopic(subtopicRepository
                        .findById(dto.getSubtopicId())
                        .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(dto.getSubtopicId()))))
                .build();

    }
}
