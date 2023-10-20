package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PersonDto {
    private String name;
    private Set<Long> subscribedTopicsIds;
    private Set<Long> postsIds;
}
