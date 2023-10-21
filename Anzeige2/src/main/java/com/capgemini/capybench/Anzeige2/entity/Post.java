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
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Subtopic subtopic;

    @ManyToOne
    private Person author;

    @ManyToMany(
            mappedBy = "subscribedPosts",
            fetch = FetchType.EAGER)
    private Set<Person> people;

    @PrePersist
    private void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }
}
