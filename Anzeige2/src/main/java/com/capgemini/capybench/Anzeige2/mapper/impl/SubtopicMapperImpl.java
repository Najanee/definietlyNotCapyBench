package com.capgemini.capybench.Anzeige2.mapper.impl;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.mapper.SubtopicMapper;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.SubtopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

@Component
public class SubtopicMapperImpl implements SubtopicMapper {

    private final PostRepository postRepository;
    private final SubtopicRepository subtopicRepository;

    @Autowired
    public SubtopicMapperImpl(PostRepository postRepository, SubtopicRepository subtopicRepository) {
        this.postRepository = postRepository;
        this.subtopicRepository = subtopicRepository;
    }

    @Override
    public SubtopicDto toDto(Subtopic entity) {
        if (entity == null){
            throw new IllegalArgumentException(SUBTOPIC_MUST_NOT_BE_NULL);
        }
        Set<Long> subtopicSubscribers = entity.getPeople().stream().map(Person::getId).collect(Collectors.toSet());
        return SubtopicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .subscriberIds(subtopicSubscribers)
                .postsIds(entity.getPosts().stream()
                        .map(Post::getId)
                        .collect(Collectors.toSet()))
                .expirationDate(entity.getExpirationDate())
                .build();
    }

    @Override
    public Subtopic toEntity(SubtopicDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(SUBTOPIC_DTO_MUST_NOT_BE_NULL);
        }
        return Subtopic.builder()
                .id(dto.getId())
                .name(dto.getName())
                .posts(dto.getPostsIds().stream()
                        .map(id -> postRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .expirationDate(dto.getExpirationDate())
                .build();
    }
}
