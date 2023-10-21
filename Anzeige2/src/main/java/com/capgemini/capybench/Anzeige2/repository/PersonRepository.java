package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
            SELECT p 
            FROM Person p 
            WHERE :id IN person_to_topic
            """)
    List<Person> findAllPeopleByTopicId(@Param("id") Long id);
}
