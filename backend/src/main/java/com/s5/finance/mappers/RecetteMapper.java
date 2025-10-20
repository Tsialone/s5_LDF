package com.s5.finance.mappers;

import org.springframework.stereotype.Component;
import com.s5.finance.dto.RecetteDTO;
import com.s5.finance.models.Recette;
import com.s5.finance.models.NatureRecette;
import com.s5.finance.models.Exercice;

@Component
public class RecetteMapper {

    public RecetteDTO toDto(Recette recette) {
        if (recette == null) return null;
        RecetteDTO dto = new RecetteDTO();
        dto.setIdRecette(recette.getIdRecette());
        dto.setIdNatureRecette(recette.getNatureRecette() != null ? recette.getNatureRecette().getIdNatureRecette() : null);
        dto.setIdExercice(recette.getExercice() != null ? recette.getExercice().getIdExercice() : null);
        dto.setMontant(recette.getMontant());
        dto.setCommentaire(recette.getCommentaire());
        return dto;
    }

    public Recette toEntity(RecetteDTO dto, NatureRecette natureRecette, Exercice exercice) {
        if (dto == null) return null;
        return Recette.builder()
                .idRecette(dto.getIdRecette())
                .natureRecette(natureRecette)
                .exercice(exercice)
                .montant(dto.getMontant())
                .commentaire(dto.getCommentaire())
                .build();
    }
}
