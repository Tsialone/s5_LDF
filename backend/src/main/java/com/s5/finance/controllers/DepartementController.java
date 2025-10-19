package com.s5.finance.controllers;

import com.s5.finance.dto.DepartementDTO;
import com.s5.finance.mappers.DepartementMapper;
import com.s5.finance.services.DepartementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departements")
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementService departementService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DepartementDTO dto) {
        try {
            DepartementDTO created = departementService.create(dto);
            return ResponseEntity.created(URI.create("/api/departements/" + created.getIdDepartement())).body(created);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage() );
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") Long id, @Valid @RequestBody DepartementDTO dto) {
        try {
            DepartementDTO updated = departementService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DepartementDTO>> list() {
        return ResponseEntity.ok(departementService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        try {
            DepartementDTO dto = departementService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        try {
            departementService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}