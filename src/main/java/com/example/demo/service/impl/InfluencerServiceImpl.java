package com.example.demo.service.impl;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerRepository repository;

    public InfluencerServiceImpl(InfluencerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        if (repository.existsBySocialHandle(influencer.getSocialHandle())) {
            throw new RuntimeException("Influencer social handle already exists");
        }
        return repository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencer) {
        Influencer existing = getInfluencerById(id);
        existing.setName(influencer.getName());
        existing.setEmail(influencer.getEmail());
        return repository.save(existing);
    }

    @Override
    public Influencer getInfluencerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found with id: " + id));
    }

    @Override
    public List<Influencer> getAllInfluencers() {
        return repository.findAll();
    }

    @Override
    public void deactivateInfluencer(Long id) {
        Influencer influencer = getInfluencerById(id);
        influencer.setActive(false);
        repository.save(influencer);
    }
}