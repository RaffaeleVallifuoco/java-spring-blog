package it.raffo.java_spring_blog.controller;

import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// public class imageController {

// @Controller
// public class FileController {

// private final String UPLOAD_DIR = "uploads/";

// @PostMapping("/upload")
// public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile)
// throws IOException {
// // Verifica se la cartella 'uploads' esiste e creala se necessario
// Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath();
// if (!Files.exists(uploadPath)) {
// Files.createDirectories(uploadPath); // Crea la cartella se non esiste
// }

// // Log per verificare il percorso assoluto
// System.out.println("Percorso assoluto della cartella uploads: " +
// uploadPath.toString());

// if (!Files.exists(uploadPath)) {
// try {
// Files.createDirectories(uploadPath); // Crea la cartella se non esiste
// System.out.println("Cartella 'uploads/' creata con successo.");
// } catch (IOException e) {
// System.err.println("Errore nella creazione della cartella: " +
// e.getMessage());
// throw new IOException("Impossibile creare la cartella per il caricamento dei
// file.");
// }
// } else {
// System.out.println("La cartella 'uploads/' esiste già.");
// }

// // Verifica se il file è vuoto
// if (imageFile.isEmpty()) {
// throw new IllegalArgumentException("Il file caricato è vuoto.");
// }

// // Crea un nome sicuro per il file
// String fileName = UUID.randomUUID().toString() + "_" +
// imageFile.getOriginalFilename().replaceAll(" ", "_");
// Path filePath = uploadPath.resolve(fileName);

// // Log per verificare il percorso del file
// System.out.println("Percorso del file da salvare: " +
// filePath.toAbsolutePath());

// // Salva il file nella cartella 'uploads'
// try {
// Files.copy(imageFile.getInputStream(), filePath);
// System.out.println("File salvato con successo in: " + filePath);
// } catch (IOException e) {
// System.err.println("Errore nel salvataggio del file: " + e.getMessage());
// throw new IOException("Impossibile salvare il file.");
// }

// // Ritorna una risposta di successo
// return "redirect:/success"; // Puoi personalizzare questa parte per
// redirigere a una pagina di successo
// }

// @GetMapping("/uploads/{filename}")
// @ResponseBody
// public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
// Path file = Paths.get(UPLOAD_DIR).resolve(filename).toAbsolutePath();
// Resource resource = new FileSystemResource(file);

// if (resource.exists()) {
// return ResponseEntity.ok()
// .contentType(MediaType.IMAGE_JPEG) // O cambia con il tipo di immagine
// corretto
// .body(resource);
// } else {
// return ResponseEntity.notFound().build();
// }
// }
// }

// }
