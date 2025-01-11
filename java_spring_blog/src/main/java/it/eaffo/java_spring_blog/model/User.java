package it.eaffo.java_spring_blog.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "Surname", length = 20, nullable = false)
    private String surname;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "userName", length = 20, nullable = false)
    private String userName;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "password", length = 16, nullable = false)
    private String password;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "photo")
    private String photoPath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> post;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<role> role;

    // GETTERS & SETTERS

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public Set<role> getRole() {
        return role;
    }

    public void setRole(Set<role> role) {
        this.role = role;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}
