package com.example.demo.service;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InfluencerService {
    
    @Autowired
    private InfluencerRepository influencerRepository;
    
    public Influencer createInfluencer(Influencer influencer) {
        // Check for duplicate social handle - using direct object
        Influencer existing = influencerRepository.findBySocialHandle(influencer.getSocialHandle());
        if (existing != null) {
            throw new RuntimeException("Duplicate social handle: " + influencer.getSocialHandle());
        }
        
        // Set default active status if not provided
        if (influencer.isActive() == null) {
            influencer.setActive(true);
        }
        
        return influencerRepository.save(influencer);
    }
    
    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Influencer not found with id: " + id));
    }
    
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }
    
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Influencer influencer = getInfluencerById(id);
        
        // Update only non-null fields
        if (influencerDetails.getName() != null) {
            influencer.setName(influencerDetails.getName());
        }
        
        if (influencerDetails.getSocialHandle() != null) {
            // Check if new social handle is unique (except for current influencer)
            Influencer existing = influencerRepository.findBySocialHandle(influencerDetails.getSocialHandle());
            if (existing != null && !existing.getId().equals(id)) {
                throw new RuntimeException("Social handle already exists: " + influencerDetails.getSocialHandle());
            }
            influencer.setSocialHandle(influencerDetails.getSocialHandle());
        }
        
        if (influencerDetails.isActive() != null) {
            influencer.setActive(influencerDetails.isActive());
        }
        
        return influencerRepository.save(influencer);
    }
    
    public boolean deleteInfluencer(Long id) {
        if (influencerRepository.existsById(id)) {
            influencerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}