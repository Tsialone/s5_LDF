package com.s5.finance.mappers;

import org.springframework.stereotype.Component;

import com.s5.finance.dto.EmployeDTO;
import com.s5.finance.models.Departement;
import com.s5.finance.models.Employe;

@Component
public class EmployeMapper {

    public EmployeDTO toDto(Employe e) {
        if (e == null)
            return null;
        EmployeDTO dto = new EmployeDTO();
        dto.setIdEmploye(e.getIdEmploye());
        dto.setNom(e.getNom());
        dto.setEmail(e.getEmail());
        dto.setIdDepartement(e.getDepartement() != null ? e.getDepartement().getIdDepartament() : null);
        return dto;
    }

    public Employe toEntity(EmployeDTO dto, Departement departement) {
        if (dto == null)
            return null;
        return Employe.builder()
                .idEmploye(dto.getIdEmploye())
                .nom(dto.getNom())
                .email(dto.getEmail())
                .departement(departement)
                .build();
    }
}
