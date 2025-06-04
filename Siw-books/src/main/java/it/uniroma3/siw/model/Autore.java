package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
