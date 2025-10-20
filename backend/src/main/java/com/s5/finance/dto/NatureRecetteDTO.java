package com.s5.finance.dto;

import lombok.Data;

@Data
public class NatureRecetteDTO {
    private Long idNatureRecette;
    private Long idCategorie;  // on stocke juste l'id pour le DTO
    private String code;
    private String libelle;
    private String description;
}
