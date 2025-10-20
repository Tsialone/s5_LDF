package com.s5.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecetteDTO {
    private Long idRecette;
    private Long idNatureRecette;
    private Long idExercice;
    private BigDecimal montant;
    private String commentaire;
}
