package com.capgemini.capybench.Anzeige2.repository;

import com.capgemini.capybench.Anzeige2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
