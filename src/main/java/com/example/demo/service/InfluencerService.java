package com.example.demo.service;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InfluencerService {

    private final InfluencerRepository influencerRepository;

    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    public Influencer createInfluencer(Influencer influencer) {
        // Check if social handle already exists
        if (influencerRepository.existsBySocialHandle(influencer.getSocialHandle())) {
            throw new RuntimeException("Social handle already exists");
        }
        
        // Set active by default
        influencer.setActive(true);
        return influencerRepository.save(influencer);
    }

    public Influencer updateInfluencer(Long id, Influencer influencer) {
        Influencer existing = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));

        // Update fields
        if (influencer.getName() != null) {
            existing.setName(influencer.getName());
        }
        if (influencer.getEmail() != null) {
            existing.setEmail(influencer.getEmail());
        }
        if (influencer.getActive() != null) {
            existing.setActive(influencer.getActive());
        }

        return influencerRepository.save(existing);
    }

    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
    }

    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }

    public void deactivateInfluencer(Long id) {
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
        influencer.setActive(false);
        influencerRepository.save(influencer);
    }
}