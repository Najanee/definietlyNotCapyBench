package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {

    @Query(
            """
                    SELECT s 
                    FROM Subtopic s 
                    WHERE s.topic.id = :topicId
                    """
    )
    List<Subtopic> findAllSubtopicsByTopicId(@Param("topicId") Long topicId);

    @Query("""
            SELECT person.id FROM Subtopic subtopic
            LEFT JOIN subtopic.people person
            WHERE subtopic.id = :subtopicId
            """)
    Set<Long> findAllSubscriberIdsOfSubtopic(@Param("subtopicId") Long subtopicId);

    @Query("""
            SELECT post.id FROM Subtopic subtopic
            LEFT JOIN subtopic.posts post
            WHERE subtopic.id = :subtopicId
            """)
    Set<Long> findAllPostIdsInSubtopic(@Param("subtopicId") Long subtopicId);
}
