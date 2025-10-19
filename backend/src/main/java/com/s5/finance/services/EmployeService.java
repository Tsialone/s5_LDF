package com.s5.finance.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.s5.finance.dto.EmployeDTO;
import com.s5.finance.mappers.EmployeMapper;
import com.s5.finance.models.Departement;
import com.s5.finance.models.Employe;
import com.s5.finance.repositories.EmployeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;
    private final EmployeMapper mapper;

    public EmployeDTO create(EmployeDTO dto, Departement dep) {
        Employe saved = employeRepository.save(mapper.toEntity(dto, dep));
        return mapper.toDto(saved);
    }

    public EmployeDTO update(Long id, EmployeDTO dto, Departement dep) throws Exception {
        Employe existing = employeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employe introuvable id=" + id));

        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        existing.setDepartement(dep);

        Employe updated = employeRepository.save(existing);
        return mapper.toDto(updated);
    }

    public EmployeDTO findById(Long id) throws Exception {
        return employeRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new Exception("Employe introuvable id=" + id));
    }

    public List<EmployeDTO> findAll() {
        return employeRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Employe existing = employeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employe introuvable id=" + id));
        employeRepository.delete(existing);
    }
}
