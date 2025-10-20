package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nature_recette")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NatureRecette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nature_recette")
    private Long idNatureRecette;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;

    @Column(length = 50)
    private String code;

    @Column(nullable = false)
    private String libelle;

    private String description;

    // Relation avec Recette
    @OneToMany(mappedBy = "natureRecette", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recette> recettes = new ArrayList<>();
}
