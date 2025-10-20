package com.s5.finance.mappers;

import org.springframework.stereotype.Component;
import com.s5.finance.dto.DetailDonDTO;
import com.s5.finance.models.DetailDon;
import com.s5.finance.models.Recette;

@Component
public class DetailDonMapper {

    public DetailDonDTO toDto(DetailDon detail) {
        if (detail == null) return null;
        DetailDonDTO dto = new DetailDonDTO();
        dto.setIdDetailDon(detail.getIdDetailDon());
        dto.setIdRecette(detail.getRecette() != null ? detail.getRecette().getIdRecette() : null);
        dto.setTypeDon(detail.getTypeDon());
        dto.setMontant(detail.getMontant());
        return dto;
    }

    public DetailDon toEntity(DetailDonDTO dto, Recette recette) {
        if (dto == null) return null;
        return DetailDon.builder()
                .idDetailDon(dto.getIdDetailDon())
                .recette(recette)
                .typeDon(dto.getTypeDon())
                .montant(dto.getMontant())
                .build();
    }
}
