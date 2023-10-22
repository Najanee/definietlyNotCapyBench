package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonById(Long personId);
}
