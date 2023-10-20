package com.capgemini.capybench.Anzeige2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    private Person person;

    @Column
    private LocalDateTime createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_to_topic",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_to_subtopic",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "subtopic_id")
    )
    private Set<Subtopic> subtopics;

    @PrePersist
    private void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }
}
