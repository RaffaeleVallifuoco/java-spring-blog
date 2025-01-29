package it.raffo.java_spring_blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.java_spring_blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
