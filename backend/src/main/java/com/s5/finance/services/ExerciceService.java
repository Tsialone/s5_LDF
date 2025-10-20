package com.s5.finance.services;

import com.s5.finance.dto.ExerciceDTO;
import com.s5.finance.mappers.ExerciceMapper;
import com.s5.finance.models.Exercice;
import com.s5.finance.repositories.ExerciceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciceService {

    private final ExerciceRepository repository;
    private final ExerciceMapper mapper;

    public ExerciceDTO create(ExerciceDTO dto) {
        Exercice entity = mapper.toEntity(dto);
        Exercice saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public ExerciceDTO update(Long id, ExerciceDTO dto) throws Exception {
        Exercice existing = repository.findById(id)
                .orElseThrow(() -> new Exception("Exercice introuvable id=" + id));
        existing.setAnnee(dto.getAnnee());
        existing.setNote(dto.getNote());
        Exercice updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    public ExerciceDTO findById(Long id) throws Exception {
        Exercice entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Exercice introuvable id=" + id));
        return mapper.toDto(entity);
    }

    public List<ExerciceDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Exercice entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Exercice introuvable id=" + id));
        repository.delete(entity);
    }
}
