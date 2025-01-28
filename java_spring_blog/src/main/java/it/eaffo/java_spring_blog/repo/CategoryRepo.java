package it.eaffo.java_spring_blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eaffo.java_spring_blog.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
