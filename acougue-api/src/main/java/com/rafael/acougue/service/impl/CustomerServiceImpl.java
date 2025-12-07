package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.Customer;
import com.rafael.acougue.dto.CustomerDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.CustomerMapper;
import com.rafael.acougue.repository.CustomerRepository;
import com.rafael.acougue.service.CustomerService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findById(UUID id) {
        return customerMapper.toDto(getCustomer(id));
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer entity = customerMapper.toEntity(dto);
        entity.setId(null);
        return customerMapper.toDto(customerRepository.save(entity));
    }

    @Override
    public CustomerDTO update(UUID id, CustomerDTO dto) {
        Customer entity = getCustomer(id);
        entity.setName(dto.getName());
        entity.setCpfCnpj(dto.getCpfCnpj());
        entity.setCreditLimit(dto.getCreditLimit());
        entity.setOutstandingBalance(dto.getOutstandingBalance());
        entity.setActive(dto.isActive());
        return customerMapper.toDto(customerRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        customerRepository.delete(getCustomer(id));
    }

    private Customer getCustomer(UUID id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado: " + id));
    }
}
