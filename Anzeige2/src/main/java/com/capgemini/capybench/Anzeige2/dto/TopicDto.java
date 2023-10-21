package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class TopicDto {
    long id;
    @NonNull String name;
    Set<SubtopicDto> subtopics;
    Set<Long> subscriberIds; // if contains ID of the requesting Person, then that Person is subscribing
    LocalDateTime expirationDate;
}
