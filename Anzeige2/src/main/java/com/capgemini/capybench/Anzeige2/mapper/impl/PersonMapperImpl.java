package com.capgemini.capybench.Anzeige2.mapper.impl;

import com.capgemini.capybench.Anzeige2.dto.PersonDto;
import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.mapper.PersonMapper;
import com.capgemini.capybench.Anzeige2.repository.PostRepository;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.PERSON_DTO_MUST_NOT_BE_NULL;
import static com.capgemini.capybench.Anzeige2.shared.MapperConstants.PERSON_MUST_NOT_BE_NULL;

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
                .id(entity.getId())
                .name(entity.getName())
                //TODO dodć do encji URLA
                .build();
    }

    @Override
    public Person toEntity(PersonDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException(PERSON_DTO_MUST_NOT_BE_NULL);
        }
        return Person.builder()
                .id(dto.getId())
                .name(dto.getName())
                // TODO: dodć do encji URLA
                .build();
    }
}
