package com.s5.finance.mappers;

import org.springframework.stereotype.Component;
import com.s5.finance.dto.NatureRecetteDTO;
import com.s5.finance.models.NatureRecette;
import com.s5.finance.models.Categorie;

@Component
public class NatureRecetteMapper {

    public NatureRecetteDTO toDto(NatureRecette nature) {
        if (nature == null) return null;
        NatureRecetteDTO dto = new NatureRecetteDTO();
        dto.setIdNatureRecette(nature.getIdNatureRecette());
        dto.setIdCategorie(nature.getCategorie() != null ? nature.getCategorie().getIdCategorie() : null);
        dto.setCode(nature.getCode());
        dto.setLibelle(nature.getLibelle());
        dto.setDescription(nature.getDescription());
        return dto;
    }

    public NatureRecette toEntity(NatureRecetteDTO dto, Categorie categorie) {
        if (dto == null) return null;
        return NatureRecette.builder()
                .idNatureRecette(dto.getIdNatureRecette())
                .categorie(categorie)
                .code(dto.getCode())
                .libelle(dto.getLibelle())
                .description(dto.getDescription())
                .build();
    }
}
