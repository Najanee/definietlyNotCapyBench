package com.capgemini.capybench.Anzeige2.service;

import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.capgemini.capybench.Anzeige2.mapper.TopicMapper;
import com.capgemini.capybench.Anzeige2.repository.TopicRepository;
import com.capgemini.capybench.Anzeige2.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    TopicMapper topicMapper;

    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    public TopicDto addTopic(TopicDto topic) {
        return null;
    }

    @Override
    public void deleteTopic(Long id) {

    }

    @Override
    public TopicDto updateTopic(TopicDto topic) {
        return null;
    }

    @Override
    public List<TopicDto> getAllTopics() {
        return null;
    }
}
