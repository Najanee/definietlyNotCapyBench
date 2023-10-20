package com.capgemini.capybench.Anzeige2.mapper;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.repository.PersonRepository;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

public class TopicMapperImpl implements TopicMapper {

    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final SubtopicRepository subtopicRepository;
    @Autowired
    private final PostRepository postRepository;

    public TopicMapperImpl(PersonRepository personRepository, SubtopicRepository subtopicRepository, PostRepository postRepository) {
        this.personRepository = personRepository;
        this.subtopicRepository = subtopicRepository;
        this.postRepository = postRepository;
    }

    @Override
    public TopicDto toDto(Topic entity) {
        if (entity == null) {
            throw new IllegalArgumentException(TOPIC_MUST_NOT_BE_NULL);
        }
        return TopicDto.builder()
                .name(entity.getName())
                .peopleIds(entity.getPeople().stream()
                        .map(Person::getId)
                        .collect(Collectors.toSet()))
                .subtopicsIds(entity.getSubtopics().stream()
                        .map(Subtopic::getId)
                        .collect(Collectors.toSet()))
                .postsIds(entity.getPosts().stream()
                        .map(Post::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Topic toEntity(TopicDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(TOPIC_DTO_MUST_NOT_BE_NULL);
        }
        return Topic.builder()
                .name(dto.getName())
                .people(dto.getPeopleIds().stream()
                        .map(id -> personRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(PERSON_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .posts(dto.getPostsIds().stream()
                        .map(id -> postRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .subtopics(dto.getSubtopicsIds().stream()
                        .map(id -> subtopicRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(SUBTOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))

                .build();
    }
}
