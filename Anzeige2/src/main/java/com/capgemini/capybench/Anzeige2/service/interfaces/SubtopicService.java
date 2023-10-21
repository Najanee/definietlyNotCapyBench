package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.SubtopicDto;

import java.util.List;

public interface SubtopicService {

    Long addSubtopic(SubtopicDto subtopicDto, Long topicId);
    List<SubtopicDto> getAllSubtopicsByTopicId(Long topicId);
    void addFollowedSubtopic(Long personId, Long subtopicId);
}
