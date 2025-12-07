package com.rafael.acougue.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDTO {

    private UUID id;
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();
}
