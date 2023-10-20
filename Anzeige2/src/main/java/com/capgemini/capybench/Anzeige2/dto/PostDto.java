package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostDto {
    private long id;
    private String title;
    private String content;
    private long personId;
    private Set<Long> topicsIds;
    private Set<Long> subtopicsIds;
}
