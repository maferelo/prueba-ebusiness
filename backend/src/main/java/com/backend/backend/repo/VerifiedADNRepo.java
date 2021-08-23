package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.VerifiedADN;

public interface VerifiedADNRepo extends JpaRepository<VerifiedADN, String> {
    List<VerifiedADN> findByCorrect(Boolean correct);
}
