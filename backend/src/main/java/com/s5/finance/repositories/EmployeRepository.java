package com.s5.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s5.finance.models.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
