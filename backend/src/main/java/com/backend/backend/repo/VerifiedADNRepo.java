package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.backend.model.VerifiedADN;

public interface VerifiedADNRepo extends JpaRepository<VerifiedADN, String> {
}
