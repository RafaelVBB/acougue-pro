package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.Customer;
import com.rafael.acougue.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO dto);
}
