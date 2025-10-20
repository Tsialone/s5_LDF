package com.s5.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.s5.finance.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
