package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.User;
import com.rafael.acougue.dto.UserDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.UserMapper;
import com.rafael.acougue.repository.UserRepository;
import com.rafael.acougue.service.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(UUID id) {
        return userMapper.toDto(getUser(id));
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User entity = userMapper.toEntity(dto);
        entity.setId(null);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public UserDTO update(UUID id, UserDTO dto) {
        User entity = getUser(id);
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRoles(dto.getRoles());
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(getUser(id));
    }

    private User getUser(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado: " + id));
    }
}
