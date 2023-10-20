package com.capgemini.capybench.Anzeige2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @ManyToOne
    private Person person;

    @Column
    private LocalDateTime createdDate;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Subtopic subtopic;

    @PrePersist
    private void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }
}
