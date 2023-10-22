package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
        SELECT DISTINCT post FROM Post post
                LEFT JOIN FETCH post.topic
                LEFT JOIN FETCH post.subtopic 
            WHERE post IN (
                SELECT DISTINCT post
                FROM Person person
                JOIN person.subscribedPosts post
                WHERE person.id = :personId
            ) OR post IN (
                SELECT DISTINCT topicPost
                FROM Person person
                JOIN person.subscribedTopics topic
                JOIN topic.posts topicPost
                WHERE person.id = :personId
            ) OR post IN (
                SELECT DISTINCT subtopicPost
                FROM Person person
                JOIN person.subscribedSubtopics subtopic
                JOIN subtopic.posts subtopicPost
                WHERE person.id = :personId
            )
            """)
    List<Post> findPostsForPerson(
        @Param("personId") Long personId);

    Post getPostById(Long postId);
}