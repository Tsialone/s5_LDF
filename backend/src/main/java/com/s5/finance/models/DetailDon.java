package com.s5.finance.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_don")
    private Long idDetailDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recette", nullable = false)
    private Recette recette;

    @Column(nullable = false)
    private String typeDon;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal montant = BigDecimal.ZERO;
}
