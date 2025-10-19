package com.s5.finance.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.s5.finance.dto.DepartementDTO;
import com.s5.finance.mappers.DepartementMapper;
import com.s5.finance.models.Departement;
import com.s5.finance.repositories.DepartementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final DepartementMapper mapper;

    public DepartementDTO create(DepartementDTO dto) {
        Departement dep = mapper.toEntity(dto);
        Departement saved = departementRepository.save(dep);
        return mapper.toDto(saved);
    }

    public DepartementDTO update(Long id, DepartementDTO dto) throws Exception {
        Departement existing = departementRepository.findById(id)
                .orElseThrow(() -> new Exception("Departement introuvable id=" + id));
        existing.setNom(dto.getNom());
        Departement updated = departementRepository.save(existing);
        return mapper.toDto(updated);
    }

    public DepartementDTO findById(Long id) throws Exception {
        Departement dep = departementRepository.findById(id)
                .orElseThrow(() -> new Exception("Departement introuvable id=" + id));
        return mapper.toDto(dep);
    }

    public List<DepartementDTO> findAll() {
        return departementRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Departement dep = departementRepository.findById(id)
                .orElseThrow(() -> new Exception("Departement introuvable id=" + id));
        departementRepository.delete(dep);
    }
}
