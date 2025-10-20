package com.s5.finance.services;

import com.s5.finance.dto.RecetteDTO;
import com.s5.finance.mappers.RecetteMapper;
import com.s5.finance.models.Exercice;
import com.s5.finance.models.NatureRecette;
import com.s5.finance.models.Recette;
import com.s5.finance.repositories.ExerciceRepository;
import com.s5.finance.repositories.NatureRecetteRepository;
import com.s5.finance.repositories.RecetteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecetteService {

    private final RecetteRepository repository;
    private final RecetteMapper mapper;
    private final NatureRecetteRepository natureRepository;
    private final ExerciceRepository exerciceRepository;

    public RecetteDTO create(RecetteDTO dto) throws Exception {
        NatureRecette nature = natureRepository.findById(dto.getIdNatureRecette())
                .orElseThrow(() -> new Exception("Nature recette introuvable id=" + dto.getIdNatureRecette()));
        Exercice exercice = exerciceRepository.findById(dto.getIdExercice())
                .orElseThrow(() -> new Exception("Exercice introuvable id=" + dto.getIdExercice()));
        Recette entity = mapper.toEntity(dto, nature, exercice);
        Recette saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public RecetteDTO update(Long id, RecetteDTO dto) throws Exception {
        Recette existing = repository.findById(id)
                .orElseThrow(() -> new Exception("Recette introuvable id=" + id));
        NatureRecette nature = natureRepository.findById(dto.getIdNatureRecette())
                .orElseThrow(() -> new Exception("Nature recette introuvable id=" + dto.getIdNatureRecette()));
        Exercice exercice = exerciceRepository.findById(dto.getIdExercice())
                .orElseThrow(() -> new Exception("Exercice introuvable id=" + dto.getIdExercice()));
        existing.setNatureRecette(nature);
        existing.setExercice(exercice);
        existing.setMontant(dto.getMontant());
        existing.setCommentaire(dto.getCommentaire());
        Recette updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    public RecetteDTO findById(Long id) throws Exception {
        Recette entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Recette introuvable id=" + id));
        return mapper.toDto(entity);
    }

    public List<RecetteDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Recette entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Recette introuvable id=" + id));
        repository.delete(entity);
    }
}
