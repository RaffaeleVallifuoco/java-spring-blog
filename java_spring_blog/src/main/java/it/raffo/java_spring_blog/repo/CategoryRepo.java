package it.raffo.java_spring_blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.java_spring_blog.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
