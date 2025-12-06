package com.rafael.acougue.repository;

import com.rafael.acougue.domain.Terminal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, UUID> {

    Optional<Terminal> findByToken(String token);

    List<Terminal> findByNameContainingIgnoreCase(String name);
}
