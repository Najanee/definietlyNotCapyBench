package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.entity.Person;

public interface PersonService {
    Person addFollowedTopic(Long topicId, Long personId);
    Person addFollowedSubtopic(Long subtopicId, Long personId);
    Person addFollowedPost(Long postId, Long personId);
    void unsubscribeFromPost(Long postId, Long personId);
    void unsubscribeFromSubtopic(Long subtopicId, Long personId);
    void unsubscribeFromTopic(Long topicId, Long personId);
}
