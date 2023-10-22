package com.capgemini.capybench.Anzeige2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Subtopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDateTime expirationDate;
    @ManyToOne
    private Topic topic;

    // Subscribers
    @ManyToMany(
            mappedBy = "subscribedSubtopics",
            fetch = FetchType.LAZY)
    private Set<Person> people;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expirationDate, topic);
    }

    // Posts belonging to the subtopic
    @OneToMany(
            mappedBy = "subtopic",
            fetch = FetchType.LAZY)
    private Set<Post> posts;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subtopic subtopic = (Subtopic) o;
        return Objects.equals(id, subtopic.id) && Objects.equals(name,
                subtopic.name) && Objects.equals(
                expirationDate,
                subtopic.expirationDate) && Objects.equals(topic, subtopic.topic);
    }
}
