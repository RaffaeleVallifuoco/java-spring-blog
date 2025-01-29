package it.raffo.java_spring_blog.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.raffo.java_spring_blog.model.Post;
import it.raffo.java_spring_blog.repo.CategoryRepo;
import it.raffo.java_spring_blog.repo.PostRepo;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class Index {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CategoryRepo categoryRepo;

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

        model.addAttribute("post", posts);

        return "home";
    }

    @GetMapping("/newPost")
    public String newPost(Model model) {
        // TODO: process POST request

        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryRepo.findAll());

        return "newPost";
    }

    @PostMapping("/newPost")
    public String savePost(@Valid @ModelAttribute("post") Post insertFormPost, BindingResult bindingResult,
            Model model) {
        // TODO: process POST request

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepo.findAll());
            return "newPost";
        }

        insertFormPost.setPost_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        postRepo.save(insertFormPost);

        return "redirect:/home";
    }

}
