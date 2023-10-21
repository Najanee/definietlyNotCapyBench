package com.capgemini.capybench.Anzeige2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class NewPostDto {
    @NonNull String title;
    @NonNull String content;
    // Przed dodaniem autor musi trafić na listę subskrybentów danego posta
    long authorId;
    long topicId;
    Long subtopicId;
    @JsonCreator
    public NewPostDto(@JsonProperty("title") String title,
                      @JsonProperty("content") String content,
                      @JsonProperty("personId") long authorId,
                      @JsonProperty("topicId") long topicId,
                      @JsonProperty("subtopicId") Long subtopicId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.topicId = topicId;
        this.subtopicId = subtopicId;
    }
}
