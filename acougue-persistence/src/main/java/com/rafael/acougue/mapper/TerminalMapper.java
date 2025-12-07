package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.Terminal;
import com.rafael.acougue.dto.TerminalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TerminalMapper {

    TerminalDTO toDto(Terminal terminal);

    Terminal toEntity(TerminalDTO dto);
}
