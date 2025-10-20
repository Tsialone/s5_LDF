package com.s5.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.s5.finance.models.Recette;

public interface RecetteRepository extends JpaRepository<Recette, Long> {
}
