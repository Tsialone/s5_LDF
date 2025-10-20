package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Long idCategorie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategorieBudget code;

    @Column(nullable = false)
    private String libelle;

    // Relation avec NatureRecette
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NatureRecette> natures = new ArrayList<>();
}
