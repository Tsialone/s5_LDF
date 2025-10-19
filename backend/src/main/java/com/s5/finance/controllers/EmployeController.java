package com.s5.finance.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.s5.finance.dto.EmployeDTO;
import com.s5.finance.mappers.DepartementMapper;
import com.s5.finance.models.Departement;
import com.s5.finance.services.DepartementService;
import com.s5.finance.services.EmployeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;
    private final DepartementService departementService;
    private final DepartementMapper departementMapper;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmployeDTO dto,
            @RequestParam(value = "departementId", required = false) Long departementId) {
        try {
            Departement dep = null;
            if (departementId != null) {
                dep = departementService.findById(departementId) != null
                        ? new Departement(departementId, null, null)
                        : null;
            }
            EmployeDTO created = employeService.create(dto, dep);
            return ResponseEntity.created(URI.create("/api/employes/" + created.getIdEmploye())).body(created);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(
            @RequestParam("id") Long id,
            @Valid @RequestBody EmployeDTO dto,
            @RequestParam(value = "departementId", required = false) Long departementId) {
        try {
            Departement dep = departementId != null
                    ? departementMapper.toEntity(departementService.findById(departementId))
                    : null;
            EmployeDTO updated = employeService.update(id, dto, dep);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeDTO>> list() {
        return ResponseEntity.ok(employeService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<?> get(@RequestParam(value = "id", required = true) Long idEmploye) {
        try {
            return ResponseEntity.ok(employeService.findById(idEmploye));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        try {
            employeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}