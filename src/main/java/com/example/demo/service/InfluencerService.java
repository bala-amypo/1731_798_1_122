// InfluencerService.java
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
        // Check for duplicate social handle
        if (influencerRepository.findBySocialHandle(influencer.getSocialHandle()) != null) {
            throw new RuntimeException("Duplicate social handle");
        }
        return influencerRepository.save(influencer);
    }
    
    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Influencer not found"));
    }
    
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }
    
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Influencer influencer = getInfluencerById(id);
        influencer.setName(influencerDetails.getName());
        influencer.setSocialHandle(influencerDetails.getSocialHandle());
        influencer.setActive(influencerDetails.isActive());
        return influencerRepository.save(influencer);
    }
}