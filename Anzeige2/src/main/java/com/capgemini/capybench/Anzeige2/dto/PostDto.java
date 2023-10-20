package com.capgemini.capybench.Anzeige2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDto {
    private String title;
    private String content;
    private long personId;
    private long topicId;
    private long subtopicId;
    @JsonCreator
    public PostDto(@JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("personId") long personId,
                   @JsonProperty("topicId") long topicId,
                   @JsonProperty("subtopic") long subtopicId) {
        this.title = title;
        this.content = content;
        this.personId = personId;
        this.topicId = topicId;
        this.subtopicId = subtopicId;
    }

}
