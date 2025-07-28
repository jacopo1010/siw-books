package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.AutoreRepository;
import it.uniroma3.siw.Repository.LibroRepository;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class LibroService {

    @Autowired private LibroRepository libroRepository;
    @Autowired private AutoreRepository autoreRepository;

    private List<String> gestioniImmagini(List<MultipartFile> file) {
        Date createdAt = new Date();
        List<String> nomiSalvati = new ArrayList<>(file.size());

        for (MultipartFile image : file) {
            if (image.isEmpty()) continue;

            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

            try {
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = image.getInputStream()) {
                    Path filePath = uploadPath.resolve(storageFileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    nomiSalvati.add(storageFileName);
                }

            } catch (Exception ex) {
                System.out.println("Errore durante il salvataggio immagine: " + ex.getMessage());
            }
        }

        return nomiSalvati;
    }

    @Transactional
    public Libro creaLibro(String titolo, LocalDate t,List<Autore> autori,List<MultipartFile> immagini){
        Libro libro = new Libro(titolo,t,autori);
        for(Autore a : autori){
            a.addLibriScritti(libro);
        }
        System.err.println(immagini.size());
        if (immagini != null && !immagini.isEmpty()) {
            for (String nome : gestioniImmagini(immagini)) {
                System.err.println(nome);
                libro.addFoto(nome); // salva il nome effettivamente scritto su disco
            }
        }
        libro = this.libroRepository.save(libro);
        return libro;
    }

    @Transactional
    public void addAutore(Long autoreId, Long libroId) {
        Autore autore = this.autoreRepository.findById(autoreId).orElseThrow(() -> new IllegalArgumentException("Autore non trovato"));
        Libro libro = this.libroRepository.findById(libroId).orElseThrow(() -> new IllegalArgumentException("Libro non trovato"));
        libro.addAutore(autore);
        this.libroRepository.save(libro);
    }


    public Libro getLibro(Long Id){
       //   return this.libroRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Libro non trovato"));
        Optional<Libro> libro = this.libroRepository.findById(Id);
        if(libro.isPresent()){
            return libro.get();
        }else {
            throw new IllegalArgumentException("Libro non trovato");
        }
    }

    public List<Libro> getAllLibri(){
         return this.libroRepository.findAll();
    }

    public List<Libro> listAllKeyWord(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        return libroRepository.searchByKeyword(keyword);
    }


    public Libro saveLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    @Transactional
    public void deleteLibro(Libro libro){
        cancellaImmagini(libro);
        this.libroRepository.delete(libro);
    }

    public List<Libro> getUltimiLibriInseriti(){
        return this.libroRepository.findTop10ByOrderByOraEDataCreazioneDesc();
    }

    private void cancellaImmagini(Libro daCancellare) {
        List<String> immagini = daCancellare.getImmagine(); // lista delle immagini
        if (immagini == null || immagini.isEmpty()) return;

        for (String nomeImmagine : immagini) {
            Path imagePath = Paths.get("public/images/" + nomeImmagine);

            try {
                Files.deleteIfExists(imagePath); // evita eccezioni se il file non esiste
                System.out.println("Cancellata immagine: " + nomeImmagine);
            } catch (IOException ex) {
                System.err.println("Errore durante la cancellazione: " + nomeImmagine + " - " + ex.getMessage());
            }
        }
    }

    public List<Libro> getLibriMiglioriRecensioni(){
        return  this.libroRepository.findByMediaVotiRecensioneMigliore();
    }

}
