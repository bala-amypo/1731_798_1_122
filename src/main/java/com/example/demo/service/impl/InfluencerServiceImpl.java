package com.example.demo.service.impl;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;

import java.util.List;

public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerRepository influencerRepository;

    // 🚨 EXACT constructor signature (tests depend on this)
    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        influencerRepository.findBySocialHandle(influencer.getSocialHandle())
                .ifPresent(i -> {
                    throw new RuntimeException("Influencer already exists");
                });
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencer) {
        Influencer existing = getInfluencerById(id);
        existing.setName(influencer.getName());
        existing.setEmail(influencer.getEmail());
        existing.setSocialHandle(influencer.getSocialHandle());
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
        Influencer influencer = getInfluencerById(id);
        influencer.setActive(false);
        influencerRepository.save(influencer);
    }
}
