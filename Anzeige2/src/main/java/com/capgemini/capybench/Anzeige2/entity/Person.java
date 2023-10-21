package com.capgemini.capybench.Anzeige2.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY)
    private Set<Post> authoredPosts;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_to_topic",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> subscribedTopics;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_to_subtopic",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "subtopic_id")
    )
    private Set<Subtopic> subscribedSubtopics;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_to_post",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> subscribedPosts;

    public void addTopic(Topic topic){
        subscribedTopics.add(topic);
        Set<Person> people = topic.getPeople();
        people.add(this);
    }
    public void addSubtopic(Subtopic subtopic){
        subscribedSubtopics.add(subtopic);
        subtopic.getPeople().add(this);
    }
    public void addPost(Post post){
        subscribedPosts.add(post);
        post.getPeople().add(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
