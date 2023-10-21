package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {

    @Query(
            """
                    SELECT s 
                    FROM Subtopic s 
                    WHERE s.topic.id = :topicId
                    """
    )
    List<Subtopic> findAllSubtopicsByTopicId(@Param("topicId") Long topicId);
}
