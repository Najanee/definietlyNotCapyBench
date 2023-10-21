package com.capgemini.capybench.Anzeige2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY)
    private Set<Post> authoredPosts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_to_topic",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> subscribedTopics;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_to_subtopic",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "subtopic_id")
    )
    private Set<Subtopic> subscribedSubtopics;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_to_post",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> subscribedPosts;
}
