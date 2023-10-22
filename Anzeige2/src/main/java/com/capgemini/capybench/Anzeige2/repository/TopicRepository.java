package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic getTopicById(Long topicId);

    @Query("""
            SELECT person.id FROM Topic topic
            LEFT JOIN topic.people person
            WHERE topic.id = :topicId
            """)
    Set<Long> findDirectSubscriberIdsOfTopic(@Param("topicId") Long topicId);

    @Query("""
            SELECT DISTINCT post.id FROM Post post
            WHERE post IN (
                SELECT DISTINCT post
                FROM Topic topic
                JOIN topic.posts post
                WHERE topic.id = :topicId
            ) OR post IN (
                SELECT DISTINCT subtopicPost
                FROM Topic topic
                JOIN topic.subtopics subtopic
                JOIN subtopic.posts subtopicPost
                WHERE topic.id = :topicId
            )
            """)
    Set<Long> findAllPostIdsInTopic(@Param("topicId") Long topicId);
}
