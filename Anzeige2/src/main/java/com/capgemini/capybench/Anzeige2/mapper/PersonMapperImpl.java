package com.capgemini.capybench.Anzeige2.mapper;

import com.capgemini.capybench.Anzeige2.dto.PersonDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Autowired
    private final TopicRepository topicRepository;
    @Autowired
    private final PostRepository postRepository;

    public PersonMapperImpl(TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    @Override
    public PersonDto toDto(Person entity) {
        if (entity == null) {
            throw new IllegalArgumentException(PERSON_MUST_NOT_BE_NULL);
        }
        return PersonDto.builder()
                .name(entity.getName())
                .postsIds(entity.getSubscribedPosts().stream()
                    .map(Post::getId)
                        .collect(Collectors.toSet()))
                .subscribedTopicsIds(entity.getSubscribedTopics().stream()
                        .map(Topic::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Person toEntity(PersonDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(PERSON_DTO_MUST_NOT_BE_NULL);
        }
        return Person.builder()
                .name(dto.getName())
                .subscribedPosts(dto.getPostsIds().stream()
                        .map(id -> postRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .subscribedTopics(dto.getSubscribedTopicsIds().stream()
                        .map(id -> topicRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(TOPIC_ENTITY_WITH_ID_S_NOT_FOUND.formatted(id))))
                .collect(Collectors.toSet()))
                .build();
    }
}
