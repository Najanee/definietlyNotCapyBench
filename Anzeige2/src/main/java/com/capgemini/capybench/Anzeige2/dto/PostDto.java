package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class PostDto {
    long id;
    @NonNull String title;
    @NonNull String content;
    @NonNull LocalDateTime createDate;
    @NonNull PersonDto author;
    LocalDateTime expirationDate;
    Set<Long> subscriberIds; // if contains ID of the requesting Person, then that Person is subscribing
    long topicId;
    Long subtopicId;
}
