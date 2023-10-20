package com.capgemini.capybench.Anzeige2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subtopic {
    @Id
    private long id;
    @Column
    private String name;

}
