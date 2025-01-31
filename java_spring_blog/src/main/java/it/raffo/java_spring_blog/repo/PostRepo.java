package it.raffo.java_spring_blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.raffo.java_spring_blog.model.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByBodyContainingIgnoreCase(String body);

    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByCategory_CategoryNameContainingIgnoreCase(String categoryName);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :searchContent, '%')) " +
            "OR LOWER(p.body) LIKE LOWER(CONCAT('%', :searchContent, '%'))")
    List<Post> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(@Param("searchContent") String searchContent);

}
