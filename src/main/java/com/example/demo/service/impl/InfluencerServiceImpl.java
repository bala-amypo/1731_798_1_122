package com.example.demo.service.impl;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerRepository influencerRepository;

    @Autowired
    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        if (influencerRepository.existsBySocialHandle(influencer.getSocialHandle())) {
            throw new RuntimeException("Social handle already exists");
        }
        if (influencerRepository.existsByEmail(influencer.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        influencer.setActive(true);
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencer) {
        Influencer existing = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));

        if (influencer.getName() != null) {
            existing.setName(influencer.getName());
        }
        if (influencer.getEmail() != null && !existing.getEmail().equals(influencer.getEmail())) {
            if (influencerRepository.existsByEmail(influencer.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            existing.setEmail(influencer.getEmail());
        }
        if (influencer.getSocialHandle() != null && !existing.getSocialHandle().equals(influencer.getSocialHandle())) {
            if (influencerRepository.existsBySocialHandle(influencer.getSocialHandle())) {
                throw new RuntimeException("Social handle already exists");
            }
            existing.setSocialHandle(influencer.getSocialHandle());
        }
        if (influencer.getActive() != null) {
            existing.setActive(influencer.getActive());
        }

        return influencerRepository.save(existing);
    }

    @Override
    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
    }

    @Override
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }

    @Override
    public void deactivateInfluencer(Long id) {
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
        influencer.setActive(false);
        influencerRepository.save(influencer);
    }
}