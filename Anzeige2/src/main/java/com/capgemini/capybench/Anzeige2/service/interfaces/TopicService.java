package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;

import java.util.List;

public interface TopicService {

    TopicDto addTopic(TopicDto topic);
    void deleteTopic(Long id);
    TopicDto updateTopic(TopicDto topic);
    List<TopicDto> getAllTopics();
}
