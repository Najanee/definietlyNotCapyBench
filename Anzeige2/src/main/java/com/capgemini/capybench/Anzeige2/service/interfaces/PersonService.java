package com.capgemini.capybench.Anzeige2.service.interfaces;

import com.capgemini.capybench.Anzeige2.entity.Person;

public interface PersonService {
    public Person addFollowedTopic(Long topicId, Long personId);
    public Person addFollowedSubtopic(Long subtopicId, Long personId);
    public Person addFollowedPost(Long postId, Long personId);
}
