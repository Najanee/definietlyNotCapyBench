package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.entity.Topic;

public interface TopicService {

    Topic addTopic(Topic topic);
    void deleteTopic(Long id);
    Topic getTopic(Long id);
    Topic updateTopic(Topic topic);
}
