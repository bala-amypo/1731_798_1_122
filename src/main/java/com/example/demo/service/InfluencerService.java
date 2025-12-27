// // InfluencerService.java
// package com.example.demo.service;

// import com.example.demo.model.Influencer;
// import com.example.demo.repository.InfluencerRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class InfluencerService {
//     @Autowired
//     private InfluencerRepository influencerRepository;
    
//     public Influencer createInfluencer(Influencer influencer) {
//         // Check for duplicate social handle
//         if (influencerRepository.findBySocialHandle(influencer.getSocialHandle()) != null) {
//             throw new RuntimeException("Duplicate social handle");
//         }
//         return influencerRepository.save(influencer);
//     }
    
//     public Influencer getInfluencerById(Long id) {
//         return influencerRepository.findById(id)
//             .orElseThrow(() -> new RuntimeException("Influencer not found"));
//     }
    
//     public List<Influencer> getAllInfluencers() {
//         return influencerRepository.findAll();
//     }
    
//     public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
//         Influencer influencer = getInfluencerById(id);
//         influencer.setName(influencerDetails.getName());
//         influencer.setSocialHandle(influencerDetails.getSocialHandle());
//         influencer.setActive(influencerDetails.isActive());
//         return influencerRepository.save(influencer);
//     }
// }



package com.example.demo.service;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InfluencerService {
    
    @Autowired
    private InfluencerRepository influencerRepository;
    
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }
    
    public Influencer getInfluencerById(Long id) {
        Optional<Influencer> influencer = influencerRepository.findById(id);
        return influencer.orElse(null);
    }
    
    public Influencer createInfluencer(Influencer influencer) {
        // Check if social handle is unique
        Optional<Influencer> existing = influencerRepository.findBySocialHandle(influencer.getSocialHandle());
        if (existing.isPresent()) {
            throw new RuntimeException("Social handle already exists: " + influencer.getSocialHandle());
        }
        
        // Set default active status if not provided
        if (influencer.isActive() == null) {
            influencer.setActive(true);
        }
        
        return influencerRepository.save(influencer);
    }
    
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Optional<Influencer> optionalInfluencer = influencerRepository.findById(id);
        if (optionalInfluencer.isEmpty()) {
            return null;
        }
        
        Influencer influencer = optionalInfluencer.get();
        
        // Update fields
        if (influencerDetails.getName() != null) {
            influencer.setName(influencerDetails.getName());
        }
        
        if (influencerDetails.getSocialHandle() != null) {
            // Check if new social handle is unique
            Optional<Influencer> existing = influencerRepository.findBySocialHandle(influencerDetails.getSocialHandle());
            if (existing.isPresent() && !existing.get().getId().equals(id)) {
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