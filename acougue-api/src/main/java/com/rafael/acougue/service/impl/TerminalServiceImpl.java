package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.Terminal;
import com.rafael.acougue.dto.TerminalDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.TerminalMapper;
import com.rafael.acougue.repository.TerminalRepository;
import com.rafael.acougue.service.TerminalService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TerminalServiceImpl implements TerminalService {

    private final TerminalRepository terminalRepository;
    private final TerminalMapper terminalMapper;

    public TerminalServiceImpl(TerminalRepository terminalRepository, TerminalMapper terminalMapper) {
        this.terminalRepository = terminalRepository;
        this.terminalMapper = terminalMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TerminalDTO> findAll() {
        return terminalRepository.findAll().stream().map(terminalMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TerminalDTO findById(UUID id) {
        return terminalMapper.toDto(getTerminal(id));
    }

    @Override
    public TerminalDTO create(TerminalDTO dto) {
        Terminal entity = terminalMapper.toEntity(dto);
        entity.setId(null);
        return terminalMapper.toDto(terminalRepository.save(entity));
    }

    @Override
    public TerminalDTO update(UUID id, TerminalDTO dto) {
        Terminal entity = getTerminal(id);
        entity.setName(dto.getName());
        entity.setToken(dto.getToken());
        entity.setLastSyncAt(dto.getLastSyncAt());
        entity.setActive(dto.isActive());
        return terminalMapper.toDto(terminalRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        terminalRepository.delete(getTerminal(id));
    }

    private Terminal getTerminal(UUID id) {
        return terminalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Terminal não encontrado: " + id));
    }
}
