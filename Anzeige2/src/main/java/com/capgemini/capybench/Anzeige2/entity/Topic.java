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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime expirationDate;

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

    public void addSubscriber(Person person){
        people.add(person);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) && Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
