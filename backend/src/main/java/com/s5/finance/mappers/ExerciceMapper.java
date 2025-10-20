package com.s5.finance.mappers;

import org.springframework.stereotype.Component;
import com.s5.finance.dto.ExerciceDTO;
import com.s5.finance.models.Exercice;

@Component
public class ExerciceMapper {

    public ExerciceDTO toDto(Exercice exercice) {
        if (exercice == null) return null;
        ExerciceDTO dto = new ExerciceDTO();
        dto.setIdExercice(exercice.getIdExercice());
        dto.setAnnee(exercice.getAnnee());
        dto.setNote(exercice.getNote());
        return dto;
    }

    public Exercice toEntity(ExerciceDTO dto) {
        if (dto == null) return null;
        return Exercice.builder()
                .idExercice(dto.getIdExercice())
                .annee(dto.getAnnee())
                .note(dto.getNote())
                .build();
    }
}
