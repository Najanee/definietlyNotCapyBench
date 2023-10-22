package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Person;
import com.capgemini.capybench.Anzeige2.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic getTopicById(Long topicId);

    @Query("""
            SELECT person.id FROM Topic topic
            LEFT JOIN topic.people person
            WHERE topic.id = :topicId
            """)
    Set<Long> findAllSubscriberIdsOfTopic(@Param("topicId") Long topicId);
}