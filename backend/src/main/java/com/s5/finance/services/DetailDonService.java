package com.s5.finance.services;

import com.s5.finance.dto.DetailDonDTO;
import com.s5.finance.mappers.DetailDonMapper;
import com.s5.finance.models.DetailDon;
import com.s5.finance.models.Recette;
import com.s5.finance.repositories.DetailDonRepository;
import com.s5.finance.repositories.RecetteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DetailDonService {

    private final DetailDonRepository repository;
    private final DetailDonMapper mapper;
    private final RecetteRepository recetteRepository;

    public DetailDonDTO create(DetailDonDTO dto) throws Exception {
        Recette recette = recetteRepository.findById(dto.getIdRecette())
                .orElseThrow(() -> new Exception("Recette introuvable id=" + dto.getIdRecette()));
        DetailDon entity = mapper.toEntity(dto, recette);
        DetailDon saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public DetailDonDTO update(Long id, DetailDonDTO dto) throws Exception {
        DetailDon existing = repository.findById(id)
                .orElseThrow(() -> new Exception("DetailDon introuvable id=" + id));
        Recette recette = recetteRepository.findById(dto.getIdRecette())
                .orElseThrow(() -> new Exception("Recette introuvable id=" + dto.getIdRecette()));
        existing.setRecette(recette);
        existing.setTypeDon(dto.getTypeDon());
        existing.setMontant(dto.getMontant());
        DetailDon updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    public DetailDonDTO findById(Long id) throws Exception {
        DetailDon entity = repository.findById(id)
                .orElseThrow(() -> new Exception("DetailDon introuvable id=" + id));
        return mapper.toDto(entity);
    }

    public List<DetailDonDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        DetailDon entity = repository.findById(id)
                .orElseThrow(() -> new Exception("DetailDon introuvable id=" + id));
        repository.delete(entity);
    }
}
