package com.paymycard.backend.repository;

import com.paymycard.backend.model.DashboardContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DashboardContentRepository extends JpaRepository<DashboardContent, UUID> {
    List<DashboardContent> findAllByTypeAndActiveOrderByDisplayOrderAsc(String type, boolean active);
}
