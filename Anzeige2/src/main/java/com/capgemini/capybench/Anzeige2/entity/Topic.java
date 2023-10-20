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

    @ManyToMany(
            mappedBy = "subscribedTopics",
            fetch = FetchType.EAGER)
    private Set<Person> people;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "subtopic_id")
    private Set<Subtopic> subtopics;

    @OneToMany(
            mappedBy = "topic",
            fetch = FetchType.EAGER)
    private Set<Post> posts;
}
