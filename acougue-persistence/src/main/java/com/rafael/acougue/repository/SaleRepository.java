package com.rafael.acougue.repository;

import com.rafael.acougue.domain.Sale;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

    Optional<Sale> findByExternalId(String externalId);

    List<Sale> findByTerminalId(UUID terminalId);
}
