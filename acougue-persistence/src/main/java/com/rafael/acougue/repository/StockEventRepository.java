package com.rafael.acougue.repository;

import com.rafael.acougue.domain.StockEvent;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockEventRepository extends JpaRepository<StockEvent, UUID> {

    List<StockEvent> findByProductId(UUID productId);
}
