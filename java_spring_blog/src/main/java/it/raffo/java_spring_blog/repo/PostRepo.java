package it.raffo.java_spring_blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.java_spring_blog.model.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByBodyContainingIgnoreCase(String body);

    List<Post> findByTitleContainingIgnoreCase(String title);
}
