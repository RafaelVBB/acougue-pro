package com.rafael.acougue.controller;

import com.rafael.acougue.dto.TerminalDTO;
import com.rafael.acougue.service.TerminalService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/terminals")
public class TerminalController {

    private final TerminalService terminalService;

    public TerminalController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @GetMapping
    public ResponseEntity<List<TerminalDTO>> list() {
        return ResponseEntity.ok(terminalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminalDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(terminalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TerminalDTO> create(@Valid @RequestBody TerminalDTO dto) {
        TerminalDTO created = terminalService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminalDTO> update(@PathVariable UUID id, @Valid @RequestBody TerminalDTO dto) {
        return ResponseEntity.ok(terminalService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        terminalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
