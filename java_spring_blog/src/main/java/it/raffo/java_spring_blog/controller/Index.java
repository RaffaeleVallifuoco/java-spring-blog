package it.raffo.java_spring_blog.controller;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/")
public class Index {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CategoryRepo categoryRepo;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping("/home")
    public String home(Model model, @RequestParam(name = "searchContent", required = false) String searchContent,
            @RequestParam(name = "categoryName", required = false) String categoryName) {

        List<Post> posts = new ArrayList<>();

        if ((searchContent == null || searchContent.isEmpty()) && (categoryName == null || categoryName.isEmpty())) {
            posts = postRepo.findAll();
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        } else if (categoryName == null || categoryName.isEmpty()) {
            posts = postRepo.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(searchContent);
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        } else {
            posts = postRepo.findByCategory_CategoryNameContainingIgnoreCase(categoryName);
            posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        }

        model.addAttribute("post", posts);
        List<Post> lastFourPosts = posts.size() > 4 ? posts.subList(posts.size() - 4, posts.size()) : posts;
        model.addAttribute("lastFourPost", lastFourPosts);

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
            @RequestParam("image") MultipartFile image,
            Model model) throws IOException {

        // Se ci sono errori di validazione nel post, tornare alla pagina di inserimento
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepo.findAll());
            return "newPost";
        }

        // Verifica se l'immagine è presente
        if (!image.isEmpty()) {
            // Creiamo la cartella uploads se non esiste
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath();
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath); // Crea la cartella se non esiste
                    System.out.println("Cartella 'uploads/' creata con successo.");
                } catch (IOException e) {
                    System.err.println("Errore nella creazione della cartella: " + e.getMessage());
                    throw new IOException("Impossibile creare la cartella per il caricamento dei file.");
                }
            }

            // Genera un nome unico per il file
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename().replaceAll(" ", "_");
            Path path = Path.of(UPLOAD_DIR + fileName); // Costruisce il percorso completo del file

            // Copia il file nella cartella uploads
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Salva il nome del file nel database
            insertFormPost.setImageUrl(fileName);
        }

        // Imposta la data del post
        insertFormPost.setPost_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        // Salva il post nel database
        postRepo.save(insertFormPost);

        return "redirect:/home";
    }

}
