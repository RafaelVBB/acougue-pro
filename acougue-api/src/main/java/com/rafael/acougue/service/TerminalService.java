package com.rafael.acougue.service;

import com.rafael.acougue.dto.TerminalDTO;
import java.util.List;
import java.util.UUID;

public interface TerminalService {

    List<TerminalDTO> findAll();

    TerminalDTO findById(UUID id);

    TerminalDTO create(TerminalDTO dto);

    TerminalDTO update(UUID id, TerminalDTO dto);

    void delete(UUID id);
}
