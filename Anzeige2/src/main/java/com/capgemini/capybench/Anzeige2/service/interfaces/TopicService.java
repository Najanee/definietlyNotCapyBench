package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;

import java.util.List;

public interface TopicService {

    Long addTopic(TopicDto topic);
    void deleteTopic(Long id);
    Long updateTopic(TopicDto topic);
    List<TopicDto> getAllTopics();
    TopicDto findTopicById(Long topicId);
    void addFollowedTopic(Long personId, Long topicId);
}
