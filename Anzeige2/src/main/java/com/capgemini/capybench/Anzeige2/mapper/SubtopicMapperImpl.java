package com.capgemini.capybench.Anzeige2.mapper;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;
import com.capgemini.capybench.Anzeige2.entity.Post;
import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.*;

public class SubtopicMapperImpl implements SubtopicMapper {
    @Autowired
    private final PostRepository postRepository;

    public SubtopicMapperImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public SubtopicDto toDto(Subtopic entity) {
        if (entity == null){
            throw new IllegalArgumentException(SUBTOPIC_MUST_NOT_BE_NULL);
        }
        return SubtopicDto.builder()
                .name(entity.getName())
                .postsIds(entity.getPosts().stream()
                        .map(Post::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Subtopic toEntity(SubtopicDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(SUBTOPIC_DTO_MUST_NOT_BE_NULL);
        }
        return Subtopic.builder()
                .name(dto.getName())
                .posts(dto.getPostsIds().stream()
                        .map(id -> postRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(POST_ENTITY_WITH_ID_S_NOR_FOUND.formatted(id))))
                        .collect(Collectors.toSet()))
                .build();
    }
}
