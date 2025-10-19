package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departement")
    private Long idDepartament;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Employe> employes = new ArrayList<>();
}