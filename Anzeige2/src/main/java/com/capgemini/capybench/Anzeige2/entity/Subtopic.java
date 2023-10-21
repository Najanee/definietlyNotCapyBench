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
public class Subtopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @ManyToOne
    private Topic topic;

    // Subscribers
    @ManyToMany(
            mappedBy = "subscribedSubtopics",
            fetch = FetchType.LAZY)
    private Set<Person> people;

    // Posts belonging to the subtopic
    @OneToMany(
            mappedBy = "subtopic",
            fetch = FetchType.LAZY)
    private Set<Post> posts;

}
