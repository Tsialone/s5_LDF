package com.s5.finance.dto;

import com.s5.finance.models.CategorieBudget;
import lombok.Data;

@Data
public class CategorieDTO {
    private Long idCategorie;
    private CategorieBudget code;
    private String libelle;
}
