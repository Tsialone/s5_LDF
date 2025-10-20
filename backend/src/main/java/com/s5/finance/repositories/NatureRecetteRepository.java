package com.s5.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.s5.finance.models.NatureRecette;

public interface NatureRecetteRepository extends JpaRepository<NatureRecette, Long> {
}
