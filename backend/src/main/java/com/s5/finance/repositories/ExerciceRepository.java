package com.s5.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.s5.finance.models.Exercice;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
}
