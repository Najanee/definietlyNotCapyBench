package com.capgemini.capybench.Anzeige2.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @Column
    private LocalDateTime expirationDate;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    @Nullable
    private Subtopic subtopic;

    @ManyToOne
    private Person author;

    //subscribers
    @ManyToMany(
            mappedBy = "subscribedPosts",
            fetch = FetchType.LAZY)
    private Set<Person> people;

    @PrePersist
    private void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title,
                                                             post.title) && Objects.equals(
                content,
                post.content) && Objects.equals(createdDate, post.createdDate) && Objects.equals(
                expirationDate,
                post.expirationDate) && Objects.equals(topic, post.topic) && Objects.equals(subtopic,
                                                                                            post.subtopic) && Objects.equals(
                author,
                post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createdDate, expirationDate, topic, subtopic, author);
    }

}
