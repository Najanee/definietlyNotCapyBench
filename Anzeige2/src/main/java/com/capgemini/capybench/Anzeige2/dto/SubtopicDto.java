package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SubtopicDto {
    private long id;
    private String name;
    private Set<Long> postsIds;
}
