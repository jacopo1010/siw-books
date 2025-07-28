package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.AutoreRepository;
import it.uniroma3.siw.Repository.LibroRepository;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private LibroRepository libroRepository;

    public List<Autore> getAllAutori() {
        return this.autoreRepository.findAll();
    }

    @Transactional
    public Autore creaAutore(String nome, String cognome, LocalDateTime dataNascita, LocalDateTime dataMorte, String nazionalita, MultipartFile image) {
        Autore nuovo = new Autore(nome, cognome, dataNascita, dataMorte, nazionalita);
        nuovo.setUrl_foto(this.aggiungiFotoAutore(image));
        nuovo = this.autoreRepository.save(nuovo);
        return nuovo;
    }

    public Autore getAutore(Long id) {
        return this.autoreRepository.findById(id).get();
    }

    @Transactional
    public void AddLibro(Long libroId, Long idAutore) {
        Libro daAggiungere = this.libroRepository.findById(libroId).orElse(null);
        Autore autore = this.autoreRepository.findById(idAutore).orElse(null);
        autore.addLibriScritti(daAggiungere);
        this.autoreRepository.save(autore);
    }

    public List<Autore> listAllKeyword(String keyWord) {
        if (keyWord != null) {
            return this.autoreRepository.searchByKeyword(keyWord);
        }
        return this.getAllAutori();
    }

    public List<Autore> listAllById(List<Long> ids) {
        return this.autoreRepository.findAllById(ids);
    }

    @Transactional
    public void deleteAutore(Long autoreId) {
        Autore daCancellare = autoreRepository.findById(autoreId)
                .orElseThrow(() -> new IllegalArgumentException("Autore non trovato"));

        /**
         * itero su i libri che ha scritto l'autore che vogliamo cancellare
         * e su ogni libro prendiamo la propria lista e rimuoviamo l'autore da cancellare stess.
         */
        for (Libro libro : daCancellare.getLibriScritti()) {
            libro.getScrittori().remove(daCancellare);
        }
        /**
         * comando in più per essere sicure che l'abbia cancellato
         */
        daCancellare.getLibriScritti().clear();
        this.cancellaImmagineAutore(daCancellare);
        this.autoreRepository.delete(daCancellare);
    }

    public void salva(Autore autore) {
        this.autoreRepository.save(autore);
    }

    public String aggiungiFotoAutore(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File non valido o vuoto");
        }
        MultipartFile image = file;
        Date createdAt = new Date();

        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";    //directory dove salvare le immagini
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return storageFileName;
    }

    private void cancellaImmagineAutore(Autore daCancellare) {
        try {
            Path imagePath = Paths.get("public/images/" + daCancellare.getUrl_foto());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
