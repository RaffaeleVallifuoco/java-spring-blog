package it.eaffo.java_spring_blog.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import it.eaffo.java_spring_blog.model.Post;
import it.eaffo.java_spring_blog.repo.PostRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class Index {

    @Autowired
    PostRepo postRepo;

    @GetMapping("/home")
    public String home(Model model, @RequestParam(name = "postTitle", required = false) String postTitle,
            @RequestParam(name = "contentBody", required = false) String contentBody) {

        List<Post> posts = new ArrayList<>();

        if (postTitle == null && contentBody == null) {
            posts = postRepo.findAll();
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        } else if (postTitle == null) {
            posts = postRepo.findByBodyContainingIgnoreCase(contentBody);
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        } else {
            posts = postRepo.findByTitleContainingIgnoreCase(postTitle);
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        }

        model.addAttribute("posts", posts);

        return "home";
    }

}
