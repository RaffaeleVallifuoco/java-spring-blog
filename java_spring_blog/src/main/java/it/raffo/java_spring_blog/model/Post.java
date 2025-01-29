package it.raffo.java_spring_blog.model;

import it.raffo.java_spring_blog.model.Category;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "body", length = 20000, nullable = false)
    private String body;

    // @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "photo", nullable = true)
    private String photoPath;

    @Column(name = "date", nullable = true)
    private LocalDateTime post_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonBackReference
    private Category category;

    // GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}