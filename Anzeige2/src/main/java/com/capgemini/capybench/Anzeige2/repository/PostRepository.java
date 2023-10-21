package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            SELECT DISTINCT p FROM Post p
            WHERE p.topic.id IN :topics OR
             p.subtopic.id IN :subtopics OR 
             p.id IN :posts
            """)
    List<Post> findAllBy(
            @Param("topics") List<Long> topics,
            @Param("subtopics") List<Long> subtopics,
            @Param("posts") List<Long> posts);
    }
