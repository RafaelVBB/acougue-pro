package com.rafael.acougue.service;

import com.rafael.acougue.dto.CustomerDTO;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(UUID id);

    CustomerDTO create(CustomerDTO dto);

    CustomerDTO update(UUID id, CustomerDTO dto);

    void delete(UUID id);
}
