package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercice")
    private Long idExercice;

    @Column(nullable = false, unique = true)
    private Integer annee;

    private String note;

    // Relation avec Recette
    @OneToMany(mappedBy = "exercice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recette> recettes = new ArrayList<>();
}
