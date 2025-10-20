package com.s5.finance.mappers;

import org.springframework.stereotype.Component;
import com.s5.finance.dto.CategorieDTO;
import com.s5.finance.models.Categorie;

@Component
public class CategorieMapper {

    public CategorieDTO toDto(Categorie categorie) {
        if (categorie == null) return null;
        CategorieDTO dto = new CategorieDTO();
        dto.setIdCategorie(categorie.getIdCategorie());
        dto.setCode(categorie.getCode());
        dto.setLibelle(categorie.getLibelle());
        return dto;
    }

    public Categorie toEntity(CategorieDTO dto) {
        if (dto == null) return null;
        return Categorie.builder()
                .idCategorie(dto.getIdCategorie())
                .code(dto.getCode())
                .libelle(dto.getLibelle())
                .build();
    }
}
