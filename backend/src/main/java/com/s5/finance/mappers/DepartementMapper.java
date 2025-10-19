package com.s5.finance.mappers;

import org.springframework.stereotype.Component;

import com.s5.finance.dto.DepartementDTO;
import com.s5.finance.models.Departement;

@Component
public class DepartementMapper {

    public DepartementDTO toDto(Departement dep) {
        if (dep == null) return null;
        DepartementDTO dto = new DepartementDTO();
        dto.setIdDepartement(dep.getIdDepartament());
        dto.setNom(dep.getNom());
        return dto;
    }

    public Departement toEntity(DepartementDTO dto) {
        if (dto == null) return null;
        return Departement.builder()
                .idDepartament(dto.getIdDepartement())
                .nom(dto.getNom())
                .build();
    }
}