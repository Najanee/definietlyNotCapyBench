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
    private long id;
    @Column
    private String name;

    @ManyToMany(
            mappedBy = "subtopics",
            fetch = FetchType.EAGER)
    private Set<Post> posts;

}
