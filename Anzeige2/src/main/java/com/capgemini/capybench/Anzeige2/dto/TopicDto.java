package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TopicDto {
    private String name;
    private Set<Long> peopleIds;
    private Set<Long> subtopicsIds;
    private Set<Long> postsIds;
}
