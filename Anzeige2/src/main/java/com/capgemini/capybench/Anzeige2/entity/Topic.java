package com.capgemini.capybench.Anzeige2.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Topic {
    @Id
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "topics")
    Set<User> users;

    @OneToMany
    @JoinColumn(name = "subtopic_id")
    private Set<Subtopic> subtopics;

}
