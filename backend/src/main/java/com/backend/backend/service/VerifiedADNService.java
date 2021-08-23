package com.backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backend.backend.model.VerifiedADN;
import com.backend.backend.repo.VerifiedADNRepo;

@Service
public class VerifiedADNService {
    private final VerifiedADNRepo verifiedADNRepo;

    @Autowired
    public VerifiedADNService(VerifiedADNRepo verifiedADNRepo) {
        this.verifiedADNRepo = verifiedADNRepo;
    }

    public VerifiedADN addVerifiedADN(VerifiedADN verifiedADN) {
        return verifiedADNRepo.save(verifiedADN);
    }

    public List<VerifiedADN> findAllVerifiedADNs() {
        return verifiedADNRepo.findAll();
    }

    public Map<String, String> findVerifiedADNStatistics() {
        int all = verifiedADNRepo.findAll().size();
        int correct = verifiedADNRepo.findByCorrect(true).size();
        int incorrect = all - correct;
        float ratio = (float) correct / all;
        HashMap<String, String> statistics = new HashMap<>();
        statistics.put("count_correct_dna", String.valueOf(correct));
        statistics.put("count_incorrect_dna", String.valueOf(incorrect));
        statistics.put("ratio", String.valueOf(ratio));
        return statistics;
    }
}
