package com.s5.finance.controllers;

import com.s5.finance.dto.DetailDonDTO;
import com.s5.finance.services.DetailDonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/dons")
@RequiredArgsConstructor
public class DetailDonController {

    private final DetailDonService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DetailDonDTO dto) {
        try {
            DetailDonDTO created = service.create(dto);
            return ResponseEntity.created(URI.create("/api/dons/" + created.getIdDetailDon())).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam Long id, @Valid @RequestBody DetailDonDTO dto) {
        try {
            DetailDonDTO updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DetailDonDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<?> get(@RequestParam Long id) {
        try {
            DetailDonDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
