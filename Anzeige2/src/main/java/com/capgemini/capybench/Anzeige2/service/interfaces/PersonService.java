package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.entity.Person;

public interface PersonService {
    Person addFollowedTopic(Long topicId, Long personId);
    Person addFollowedSubtopic(Long subtopicId, Long personId);
    Person addFollowedPost(Long postId, Long personId);
    String unsubscribeFromPost(Long postId, Long personId);
    String unsubscribeFromSubtopic(Long subtopicId, Long personId);
    String unsubscribeFromTopic(Long topicId, Long personId);
}
