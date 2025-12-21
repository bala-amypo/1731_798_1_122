package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerRepository influencerRepository;

    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        // Check for duplicate social handle
        if (influencerRepository.existsBySocialHandle(influencer.getSocialHandle())) {
            throw new RuntimeException("Duplicate social handle");
        }
        
        // Ensure active is true by default
        if (influencer.getActive() == null) {
            influencer.setActive(true);
        }
        
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencer) {
        Influencer existingInfluencer = influencerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
        
        // Check if social handle is being changed to an existing one
        if (influencer.getSocialHandle() != null && 
            !influencer.getSocialHandle().equals(existingInfluencer.getSocialHandle()) &&
            influencerRepository.existsBySocialHandle(influencer.getSocialHandle())) {
            throw new RuntimeException("Duplicate social handle");
        }
        
        // Update fields if provided
        if (influencer.getName() != null) {
            existingInfluencer.setName(influencer.getName());
        }
        if (influencer.getSocialHandle() != null) {
            existingInfluencer.setSocialHandle(influencer.getSocialHandle());
        }
        if (influencer.getEmail() != null) {
            existingInfluencer.setEmail(influencer.getEmail());
        }
        if (influencer.getActive() != null) {
            existingInfluencer.setActive(influencer.getActive());
        }
        
        return influencerRepository.save(existingInfluencer);
    }

    @Override
    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
    }

    @Override
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }

    @Override
    public Influencer deactivateInfluencer(Long id) {
        Influencer influencer = getInfluencerById(id);
        influencer.setActive(false);
        return influencerRepository.save(influencer);
    }
}