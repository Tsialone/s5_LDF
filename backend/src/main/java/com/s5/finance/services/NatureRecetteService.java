package com.s5.finance.services;

import com.s5.finance.dto.NatureRecetteDTO;
import com.s5.finance.mappers.NatureRecetteMapper;
import com.s5.finance.models.Categorie;
import com.s5.finance.models.NatureRecette;
import com.s5.finance.repositories.CategorieRepository;
import com.s5.finance.repositories.NatureRecetteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NatureRecetteService {

    private final NatureRecetteRepository repository;
    private final NatureRecetteMapper mapper;
    private final CategorieRepository categorieRepository;

    public NatureRecetteDTO create(NatureRecetteDTO dto) throws Exception {
        Categorie cat = categorieRepository.findById(dto.getIdCategorie())
                .orElseThrow(() -> new Exception("Catégorie introuvable id=" + dto.getIdCategorie()));
        NatureRecette entity = mapper.toEntity(dto, cat);
        NatureRecette saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public NatureRecetteDTO update(Long id, NatureRecetteDTO dto) throws Exception {
        NatureRecette existing = repository.findById(id)
                .orElseThrow(() -> new Exception("Nature recette introuvable id=" + id));
        Categorie cat = categorieRepository.findById(dto.getIdCategorie())
                .orElseThrow(() -> new Exception("Catégorie introuvable id=" + dto.getIdCategorie()));
        existing.setCategorie(cat);
        existing.setCode(dto.getCode());
        existing.setLibelle(dto.getLibelle());
        existing.setDescription(dto.getDescription());
        NatureRecette updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    public NatureRecetteDTO findById(Long id) throws Exception {
        NatureRecette entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Nature recette introuvable id=" + id));
        return mapper.toDto(entity);
    }

    public List<NatureRecetteDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        NatureRecette entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Nature recette introuvable id=" + id));
        repository.delete(entity);
    }
}
