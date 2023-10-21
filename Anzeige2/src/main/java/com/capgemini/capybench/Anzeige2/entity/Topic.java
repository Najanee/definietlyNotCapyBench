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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    // Subscribers
    @ManyToMany(
            mappedBy = "subscribedTopics",
            fetch = FetchType.LAZY)
    private Set<Person> people;

    @OneToMany(
            mappedBy = "topic",
            fetch = FetchType.LAZY)
    private Set<Subtopic> subtopics;

    @OneToMany(
            mappedBy = "topic",
            fetch = FetchType.LAZY)
    private Set<Post> posts;
}
