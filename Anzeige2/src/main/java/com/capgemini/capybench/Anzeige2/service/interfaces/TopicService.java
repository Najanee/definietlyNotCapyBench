package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;

import java.util.List;

public interface TopicService {

    Long addTopic(TopicDto topic);
    void deleteTopic(Long id);
    Long updateTopic(TopicDto topic, Long id);
    List<TopicDto> getAllTopics();
}
