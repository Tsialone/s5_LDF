package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recette", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "id_nature_recette", "id_exercice" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recette")
    private Long idRecette;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nature_recette", nullable = false)
    private NatureRecette natureRecette;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercice", nullable = false)
    private Exercice exercice;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal montant = BigDecimal.ZERO;

    private String commentaire;

    // Relation avec DetailDon
    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailDon> detailsDons = new ArrayList<>();
}
