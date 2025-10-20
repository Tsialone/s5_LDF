package com.s5.finance.services;

import com.s5.finance.dto.CategorieDTO;
import com.s5.finance.mappers.CategorieMapper;
import com.s5.finance.models.Categorie;
import com.s5.finance.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorieService {

    private final CategorieRepository repository;
    private final CategorieMapper mapper;

    public CategorieDTO create(CategorieDTO dto) {
        Categorie entity = mapper.toEntity(dto);
        Categorie saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public CategorieDTO update(Long id, CategorieDTO dto) throws Exception {
        Categorie existing = repository.findById(id)
                .orElseThrow(() -> new Exception("Catégorie introuvable id=" + id));
        existing.setCode(dto.getCode());
        existing.setLibelle(dto.getLibelle());
        Categorie updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    public CategorieDTO findById(Long id) throws Exception {
        Categorie entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Catégorie introuvable id=" + id));
        return mapper.toDto(entity);
    }

    public List<CategorieDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Categorie entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Catégorie introuvable id=" + id));
        repository.delete(entity);
    }
}
