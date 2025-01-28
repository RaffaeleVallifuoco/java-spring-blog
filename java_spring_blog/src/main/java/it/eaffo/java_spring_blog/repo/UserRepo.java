package it.eaffo.java_spring_blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eaffo.java_spring_blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
