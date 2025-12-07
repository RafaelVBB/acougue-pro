package com.rafael.acougue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rafael.acougue.domain.Customer;
import com.rafael.acougue.dto.CustomerDTO;
import com.rafael.acougue.mapper.CustomerMapper;
import com.rafael.acougue.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createShouldPersistAndReturnDto() {
        CustomerDTO dto = new CustomerDTO();
        Customer entity = new Customer();

        when(customerMapper.toEntity(dto)).thenReturn(entity);
        when(customerRepository.save(entity)).thenReturn(entity);
        when(customerMapper.toDto(entity)).thenReturn(dto);

        CustomerDTO result = customerService.create(dto);

        assertThat(result).isEqualTo(dto);
        verify(customerRepository).save(entity);
    }
}
