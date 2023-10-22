package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {

    Subtopic getSubtopicById(Long subtopicId);

    @Query("""
            SELECT subtopic FROM Subtopic subtopic
            LEFT JOIN subtopic.topic topic
            WHERE topic.id = :topicId
            """)
    List<Subtopic> findSubtopicsInTopic(@Param("topicId") Long topicId);

    @Query("""
            SELECT person.id FROM Subtopic subtopic
            LEFT JOIN subtopic.people person
            WHERE subtopic.id = :subtopicId
            """)
    Set<Long> findSubscriberIdsOfSubtopic(@Param("subtopicId") Long subtopicId);

    @Query("""
            SELECT post.id FROM Subtopic subtopic
            LEFT JOIN subtopic.posts post
            WHERE subtopic.id = :subtopicId
            """)
    Set<Long> findPostIdsInSubtopic(@Param("subtopicId") Long subtopicId);
}
